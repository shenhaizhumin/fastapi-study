package com.example.lifecycle;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class User extends BaseObservable {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Integer> age = new ObservableField<>();

    public User(String name, Integer age) {
        this.name.set(name);
        this.age.set(age);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
//        notifyPropertyChanged(BR.name);
    }

    public String getAge() {
        return age.get() + "";
    }

    public void setAge(Integer age) {
        this.age.set(age);
//        notifyPropertyChanged(BR.age);
    }
}
