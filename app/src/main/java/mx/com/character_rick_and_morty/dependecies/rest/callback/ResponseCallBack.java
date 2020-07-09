package mx.com.character_rick_and_morty.dependecies.rest.callback;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallBack<T> implements Callback<T> {

    private static final String TAG = ResponseCallBack.class.getName();

    public ResponseCallBack() {
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int code = response.code();
        T result = response.body();
        switch (code) {
            case HttpURLConnection.HTTP_OK:
                if (response.isSuccessful()) {
                    onSuccess(result);
                } else {
                    onFail("No se obtuvo respuesta.");
                }
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                onFail("Unauthorized");
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                onFail("Forbbiden!");
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                onFail("Recurso no encontrado.");
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                onFail("Error de comunicaciones.");
                break;
            default:
                onFail("Servidor no disponible.");
                break;
        }
        Log.wtf(TAG, code + response.message());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof UnknownHostException)
            onFail("No es posible establecer comunicación con el servidor, verifique su conexión e intente de nuevo.");
        else
            onFail(t.getMessage());
        Log.wtf(TAG, t.getMessage());
    }

    /***
     * Métodos de repuesta exitosa
     * @param result
     */
    public abstract void onSuccess(T result);

    /***
     * Métodos de repuesta fallida
     * @param message mensaje de error
     */
    public abstract void onFail(String message);
}
