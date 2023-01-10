package com.example.du_lich_vn;

import java.io.Serializable;

public class Account implements Serializable {
    String _id,_name,_user,_pass;

    public Account(String _id, String _name, String _user, String _pass) {
        this._id = _id;
        this._name = _name;
        this._user = _user;
        this._pass = _pass;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String _pass) {
        this._pass = _pass;
    }
}
