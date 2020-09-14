package com.example.hinduhelpadmin.apiiterface;



import com.example.hinduhelpadmin.apiiterface.responce.helpresponce;
import com.example.hinduhelpadmin.apiiterface.responce.loginresponce;
import com.example.hinduhelpadmin.apiiterface.responce.prof_responce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("RegistrationControllerJson.php")
    Call<CommanResponse> createUser(

            @Field("loginRegistration") String loginRegistration,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("mobileno") String mobileno,
            @Field("email") String email,
            @Field("address") String address,
            @Field("pincode") String pincode,
            @Field("state") String state,
            @Field("dist") String dist,
            @Field("pass") String pass

    );

    @FormUrlEncoded
    @POST("help.php")
    Call<helpresponce> hcmp(

            @Field("helpadmin") String helpadmin,
            @Field("state") String state,
            @Field("dist") String dist
    );


    @FormUrlEncoded
    @POST("help.php")
    Call<CommanResponse> hupone(

            @Field("helpone") String helpone,
            @Field("hid") String hid,
            @Field("status") String status,
            @Field("name") String name,
            @Field("mobno") String mobno

    );


    @FormUrlEncoded
    @POST("help.php")
    Call<CommanResponse> huptwo(

            @Field("helptwo") String helptwo,
            @Field("hid") String hid,
            @Field("status") String status

    );

    @FormUrlEncoded
    @POST("help.php")
    Call<helpresponce> hcmpp(

            @Field("helpadcmp") String helpadcmp,
            @Field("state") String state,
            @Field("dist") String dist
    );

    @FormUrlEncoded
    @POST("help.php")
    Call<helpresponce> hpend(

            @Field("helpadpend") String helpadpend,
            @Field("state") String state,
            @Field("dist") String dist
    );



    @FormUrlEncoded
    @POST("loginapi.php")
    Call<loginresponce> login(

            @Field("loginadmin") String loginadmin,
            @Field("mobileno") String mobileno,
            @Field("pass") String pass
    );

    @FormUrlEncoded
    @POST("help.php")
    Call<CommanResponse> addhelp(

            @Field("helpadd") String helpadd,
            @Field("fullname") String fullname,
            @Field("mobileno") String mobileno,
            @Field("email") String email,
            @Field("address") String address,
            @Field("pincode") String pincode,
            @Field("state") String state,
            @Field("dist") String dist,
            @Field("typehelp") String typehelp,
            @Field("helpmode") String helpmode,
            @Field("datehelp") String datehelp,
            @Field("status") String status,
            @Field("helper") String helper,
            @Field("hmob") String hmob,
            @Field("hdisc") String hdisc,
            @Field("uid") String uid


    );

    @FormUrlEncoded
    @POST("prof.php")
    Call<prof_responce> profpass(
            @Field("profilepassupdateadmin") String profilepassupdateadmin,
            @Field("mobno") String mobno,
            @Field("oldpassword") String oldpassword,
            @Field("newpassword") String newpassword
    );

    @FormUrlEncoded
    @POST("prof.php")
    Call<loginresponce> personalupdate(

            @Field("profilepersonaladmin") String profilepersonaladmin,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("mobno") String mobno,
            @Field("email") String email,
            @Field("uid") String uid
    );



    @FormUrlEncoded
    @POST("fcm_insert.php")
    Call<CommanResponse> getFcm(
            @Field("fcm_token") String fcm_token,
            @Field("state") String state,
            @Field("dist") String dist
    );





}
