package com.poly.managershop.models;

public class Product {
    private String _id,tensanpham,giasanpham,mota,loai,anhsanpham;

    public Product() {
    }

    public Product(String _id, String tensanpham, String giasanpham, String mota, String loai, String anhsanpham) {
        this._id = _id;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.mota = mota;
        this.loai = loai;
        this.anhsanpham = anhsanpham;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getAnhsanpham() {
        return anhsanpham;
    }

    public void setAnhsanpham(String anhsanpham) {
        this.anhsanpham = anhsanpham;
    }
}
