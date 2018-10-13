package com.heroes.lesh.kidneys.FragmentsTreatments.FragmentsTreatmentsGuides;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.BuildConfig;
import com.heroes.lesh.kidneys.Interfaces.ListenerPag;
import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.Url;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreatmentsGuideFragment extends Fragment {
    //Variables globales
    private View view;
    private Bundle bundle;
    private Unbinder unbinder;
    @BindView(R.id.imageViewTreatmentsGuide)
    ImageView imageViewTreatmentsGuide;
    @BindView(R.id.textViewNameTreatmentsGuide)
    TextView textViewNameTreatmentsGuide;
    @BindView(R.id.textViewDescriptionTreatmentsGuide)
    TextView textViewDescriptionTreatmentsGuide;
    @BindView(R.id.textViewNumPagesTreatmentsGuide)
    TextView textViewNumPagesTreatmentsGuide;
    @BindView(R.id.textViewAuthorTreatmentsGuide)
    TextView textViewAuthorTreatmentsGuide;
    @BindView(R.id.textViewYearPublicationTreatmentsGuide)
    TextView textViewYearPublicationTreatmentsGuide;
    @BindView(R.id.textViewReferenceTreatmentsGuide)
    TextView textViewReferenceTreatmentsGuide;
    @BindView(R.id.floatingDownloadTreatmentsGuide)
    FloatingActionButton floatingDownloadTreatmentsGuide;
    @BindView(R.id.floatingContentTreatmentsGuide)
    FloatingActionButton floatingContentTreatmentsGuide;
    @BindView(R.id.card_view_treatments_guides_download)
    CardView card_view_treatments_guides_download;
    @BindView(R.id.textViewDescargarTreatmentsGuide)
    TextView textViewDescargarTreatmentsGuide;
    @BindView(R.id.floatingDeleteTreatmentsGuide)
    FloatingActionButton floatingDeleteTreatmentsGuide;
    @BindDrawable(R.drawable.loading)
    Drawable loadingImage;
    @BindDrawable(R.drawable.error)
    Drawable errorImage;
    @BindDrawable(R.mipmap.ic_tratamientos)
    Drawable ic_tratamientos;
    @BindDrawable(R.drawable.ic_save)
    Drawable ic_save;
    @BindDrawable(R.drawable.ic_download)
    Drawable ic_download;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindString(R.string.descargando)
    String descargando;
    @BindString(R.string.descargacompleta)
    String descargacompleta;
    @BindString(R.string.errorconexion)
    String errorconexion;
    @BindString(R.string.errorraro)
    String errorraro;
    @BindString(R.string.descargar)
    String descargar;
    @BindString(R.string.errorpdf)
    String errorpdf;
    @BindString(R.string.abrirpdf)
    String abrirpdf;
    @BindString(R.string.aceptar)
    String aceptar;
    @BindString(R.string.cancelar)
    String cancelar;
    @BindString(R.string.descargacancelada)
    String descargacancelada;
    @BindString(R.string.errorreliminar)
    String errorreliminar;
    @BindString(R.string.eliminado)
    String eliminado;
    @BindString(R.string.eliminar)
    String eliminar;
    @BindColor(R.color.colorAzul)
    int colorAzul;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRosado)
    int colorRosado;
    private DownloadPdf downloadPdf;
    private String pdf;
    private String name;
    private ProgressDialog progressDialog;
    private int writeExternalStorage;
    private int readExternalStorage;
    private final int PERMISSION_STORAGE = 1002;
    private final int PERMISSION_STORAGE_DELETE = 1003;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;
    private File file;
    private String ruteFile;
    private Intent intentView;
    private String mymeType;
    private SharedPreferences preferences;
    private String content;
    private LayoutInflater layoutInflaterAlertGuide;
    private View viewAlertGuide;
    private TextView textViewAlertTreatmentsGuides;
    private TextView textViewAlertIndexTreatmentsGuides;
    private int color;

    public TreatmentsGuideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_treatments_guide, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializar();
        inicializarToast();
        changeColors();
        bundle = getArguments();
        if (bundle != null) {
            Picasso.get().load(bundle.getString("Image")).fit().placeholder(loadingImage).error(errorImage).into(imageViewTreatmentsGuide);
            textViewNameTreatmentsGuide.setText(bundle.getString("Name"));
            name = bundle.getString("Name");
            textViewDescriptionTreatmentsGuide.append("\n" + bundle.getString("Description"));
            textViewNumPagesTreatmentsGuide.append("\n" + bundle.getInt("NumPages"));
            pdf = bundle.getString("Pdf");
            ruteFile = Environment.getExternalStorageDirectory() + "/" + name + ".pdf";
            textViewAuthorTreatmentsGuide.append("\n" + bundle.getString("Author"));
            textViewYearPublicationTreatmentsGuide.append("\n" + bundle.getString("YearPublication"));
            textViewReferenceTreatmentsGuide.append("\n" + bundle.getString("Reference"));
            content = bundle.getString("Content");
        }
        changeIconAndTextDonwload();
        floatingDownloadTreatmentsGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    checkForPermisions();
                } else {
                    if (existPdf(ruteFile)) {
                        openPdf(ruteFile);
                    } else {
                        DownloadPdf(pdf, name);
                    }
                }
            }
        });
        floatingDownloadTreatmentsGuide.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(descargar, ic_tratamientos);
                toast.show();
                return false;
            }
        });
        floatingContentTreatmentsGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForViewGuide(content);
            }
        });
        card_view_treatments_guides_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    checkForPermisions();
                } else {
                    if (existPdf(ruteFile)) {
                        openPdf(ruteFile);
                    } else {
                        DownloadPdf(pdf, name);
                    }
                }
            }
        });
        card_view_treatments_guides_download.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(descargar, ic_tratamientos);
                toast.show();
                return false;
            }
        });
        floatingDeleteTreatmentsGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    checkForPermisionsDelete();
                }else {
                    deletePdf(ruteFile);
                }
            }
        });
        floatingDeleteTreatmentsGuide.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                changeToast(eliminar,ic_tratamientos);
                toast.show();
                return false;
            }
        });
        return view;
    }

    private void inicializar() {
        preferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        color = colorAzul;
    }

    private boolean existPdf(String ruteFile) {
        file = new File(ruteFile);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    //Metodo para recuperar el color requerido
    private void changeColors() {
        switch (preferences.getInt("ColorApp", 1)) {
            case 1:
                setColorsApp(colorAzul);
                break;
            case 2:
                setColorsApp(colorMarron);
                break;
            case 3:
                setColorsApp(colorRosado);
                break;
        }
    }

    //Metodo para cambiar los colores de la app
    private void setColorsApp(int color) {
        textViewNameTreatmentsGuide.setBackgroundColor(color);
        this.color = color;
    }

    private void changeIconAndTextDonwload() {
        if (existPdf(ruteFile)) {
            floatingDownloadTreatmentsGuide.setImageDrawable(ic_save);
            textViewDescargarTreatmentsGuide.setText(abrirpdf);
            floatingDeleteTreatmentsGuide.setVisibility(View.VISIBLE);
        }else{
            floatingDownloadTreatmentsGuide.setImageDrawable(ic_download);
            textViewDescargarTreatmentsGuide.setText(descargar);
            floatingDeleteTreatmentsGuide.setVisibility(View.GONE);
        }
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    //Metodo para cambiar el toast
    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    //Pedir permiso en ejecucion
    private void checkForPermisions() {
        writeExternalStorage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        readExternalStorage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if ((writeExternalStorage != PackageManager.PERMISSION_GRANTED) || (readExternalStorage != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_STORAGE);
        }
    }

    //Pedir permiso en ejecucion
    private void checkForPermisionsDelete() {
        writeExternalStorage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        readExternalStorage = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if ((writeExternalStorage != PackageManager.PERMISSION_GRANTED) || (readExternalStorage != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_STORAGE_DELETE);
        }
    }


    //Resultado de la pregunta del permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    } else {
                        if (existPdf(ruteFile)) {
                            openPdf(ruteFile);
                        } else {
                            DownloadPdf(pdf, name);
                        }
                    }
                }
                break;
            case PERMISSION_STORAGE_DELETE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    } else {
                       deletePdf(ruteFile);
                    }
                }
                break;
        }
    }

    private void DownloadPdf(String pdf, String name) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Descargando");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setButton(cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(downloadPdf != null && downloadPdf.getStatus() != AsyncTask.Status.FINISHED){
                    downloadPdf.cancel(true);
                }
            }
        });
        downloadPdf = new DownloadPdf(progressDialog);
        downloadPdf.execute(pdf, name);
    }

    private void openPdf(String ruteFile) {
        file = new File(ruteFile);
        intentView = new Intent(Intent.ACTION_VIEW);
       mymeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(ruteFile));
        //intentView.setDataAndType(Uri.fromFile(file), mymeType); //Al parecer en android 8 ya no es asi ni en 7 quien sabe
        intentView.setDataAndType( FileProvider.getUriForFile(
                getContext(),
                getApplicationContext().getApplicationContext()
                        .getPackageName() + ".provider", file), mymeType);
        intentView.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(Intent.createChooser(intentView, abrirpdf));
            getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

        } catch (android.content.ActivityNotFoundException ex) {
            changeToast(errorpdf, ic_harmful);
            toast.show();
        }
    }
    private void deletePdf(String ruteFile){
        file = new File(ruteFile);
        if(file.delete()){
            changeToast(eliminado,ic_healthy);
            toast.show();
            changeIconAndTextDonwload();
        }else{
            changeToast(errorreliminar,ic_harmful);
            toast.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        changeIconAndTextDonwload();
    }

    //Alerta para mostrar una food
    private void showAlertForViewGuide(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        layoutInflaterAlertGuide = getActivity().getLayoutInflater();
        viewAlertGuide = layoutInflaterAlertGuide.inflate(R.layout.custom_alert_treatments_guides, null);
        textViewAlertTreatmentsGuides = viewAlertGuide.findViewById(R.id.textViewAlertTreatmentsGuides);
        textViewAlertIndexTreatmentsGuides = viewAlertGuide.findViewById(R.id.textViewAlertIndexTreatmentsGuides);
        builder.setView(viewAlertGuide);
        textViewAlertTreatmentsGuides.setBackgroundColor(color);
        textViewAlertIndexTreatmentsGuides.setText(Html.fromHtml(content));

        builder.setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class DownloadPdf extends AsyncTask<String, Integer, String> {
        private URL url;
        private HttpURLConnection conexion;
        private InputStream inputStream;
        private byte[] data;
        private int count;
        private String urlDownload;
        private OutputStream outputStream;
        private String ruteFile;
        private ProgressDialog progressDialog;
        private int total = 0;
        private int longfile;
        private String name;

        public DownloadPdf() {

        }

        public DownloadPdf(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urlPdf) {
            if(!isCancelled()) {
                urlDownload = urlPdf[0];
                name = urlPdf[1];
                try {
                    url = new URL(urlDownload);
                    conexion = (HttpURLConnection) url.openConnection();
                    conexion.connect();
                    if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        return errorconexion;
                    }
                    inputStream = conexion.getInputStream();
                    ruteFile = Environment.getExternalStorageDirectory() + "/" + name + ".pdf";
                    outputStream = new FileOutputStream(ruteFile);
                    longfile = conexion.getContentLength();
                    data = new byte[1024];
                    while ((count = inputStream.read(data)) != -1) {
                        outputStream.write(data, 0, count);
                        total += count;
                        publishProgress((int) total * 100 / longfile);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return errorraro;
                } catch (IOException e) {
                    e.printStackTrace();
                    return errorconexion;
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                        if (outputStream != null) outputStream.close();
                        if (conexion != null) conexion.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return descargacompleta;
            }else{
                return descargacancelada;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            changeToast(message, ic_tratamientos);
            toast.show();
            progressDialog.dismiss();
            if (TextUtils.equals(message, descargacompleta)) {
                if (existPdf(ruteFile)) {
                    openPdf(ruteFile);
                }else{
                    changeToast(errorraro,ic_harmful);
                    toast.show();
                }
            }
        }

        @Override
        protected void onCancelled() {
            changeToast(descargacancelada,ic_harmful);
            toast.show();
            if (existPdf(ruteFile)) {
                deletePdf(ruteFile);
            }else{
                changeToast(errorraro,ic_harmful);
                toast.show();
            }
            super.onCancelled();
        }
    }
}
