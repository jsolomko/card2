package com.example.card20.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.card20.R;
import com.example.card20.data.Card;
import com.example.card20.databinding.FragmentAddCardBinding;
import com.example.card20.ui.dashboard.ListCardViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCardFragment extends Fragment {

    private FragmentAddCardBinding binding;
    String currentPhotoPath;
    private final int REQUEST_CAMERA = 100;
    View root;
    ImageView imageView;
    EditText cardBrent, cardNumber;
    Bitmap resizedBitmap;
    Uri photoUri;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AddCardViewModel addCardViewModel = new ViewModelProvider(this).get(AddCardViewModel.class);
        binding = FragmentAddCardBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        cardBrent = root.findViewById(R.id.edCardTitle);
        cardNumber = root.findViewById(R.id.edCardNumber);

        imageView = root.findViewById(R.id.imageView);
        imageView.setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });


        Button button = root.findViewById(R.id.openCamera);

        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(cardBrent.getText())) {
                Toast.makeText(getContext(), "Заполните название", Toast.LENGTH_SHORT).show();
            } else {
                ListCardViewModel listCardViewModel =
                        new ViewModelProvider(this).get(ListCardViewModel.class);
                listCardViewModel.addCard(new Card(cardBrent.getText().toString(), photoUri.toString()));

                Toast.makeText(getContext(), "Карта добавлена", Toast.LENGTH_SHORT).show();
            }

        });
        return root;
    }

    private File getImageFile() throws IOException {
        //Временная метка для вхождения в имя файла
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //Имя файла
        String imageFileName = "JPEG_" + timeStamp + "_";
        //Дериктория для сохранения фото
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        /**Сохраняем путь для использования интенте(Передать в базу????)*/
        currentPhotoPath = image.getAbsolutePath();
        Log.d("TAG", currentPhotoPath);
        return image;
    }

    public void dispatchTakePictureIntent() {
        //Интент на открытие камеры
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //создать файл куда фото должно упасть
        File photoFile = null;
        try {
            photoFile = getImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Продлолжаем только если файл создался
        if (photoFile != null) {
            Log.d("TAG", photoFile.getAbsolutePath());
            photoUri = FileProvider.getUriForFile(requireActivity(), "com.example.card20.fileprovider", photoFile);
            //Кладем в интент путь до фото
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
        }
    }


    void setPicture() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        resizedBitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(resizedBitmap);

    }


    //Этот метод уже может обновить imageView так как только он получает результат

    /**
     * метода startActivityForResult
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setPicture();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}