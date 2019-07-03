package com.pankaj.Simpragma.Retrofit;

import com.pankaj.Simpragma.Constants.ApiConstants;



public class ApiUtils {

    public static APIService getAPIService() {

        return RetrofitClient.getClient(ApiConstants.baseurl).create(APIService.class);
    }
}
