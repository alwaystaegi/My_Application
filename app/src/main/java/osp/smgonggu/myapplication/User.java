package osp.smgonggu.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

// User class의 역할: User데이터를 담을 함수형 클래스
@IgnoreExtraProperties
/*
IgnoreExtraProperties의 역할(영문)
Properties that don't map to class fields are ignored when serializing to a class annotated with this annotation.
아마 아래 클래스와 다른 방식의 데이터가 들어오면 무시하는 역할을 하는 듯함
*/
public class User {


    public String userName;

    public User(String name) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" +  '\'' +
                '}';
    }
}