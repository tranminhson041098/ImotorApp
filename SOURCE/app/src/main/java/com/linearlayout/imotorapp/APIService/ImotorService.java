package com.linearlayout.imotorapp.APIService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ImotorService {

    //Khai bao tên Service , đối tượng truyền lên
    @POST ("Service/Login")
    Call<ResponseBody> getInfoLogin(@Body Object object);
}
