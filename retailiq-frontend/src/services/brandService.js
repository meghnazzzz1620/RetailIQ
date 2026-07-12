import api from "./api";

export const getAllBrands = async () => {

    const response = await api.get("/brands");

    return response.data;

};

export const getBrandById = async (id) => {

    const response = await api.get(`/brands/${id}`);

    return response.data;

};

export const createBrand = async (brand) => {

    const response = await api.post("/brands", brand);

    return response.data;

};

export const updateBrand = async (id, brand) => {

    const response = await api.put(`/brands/${id}`, brand);

    return response.data;

};

export const deleteBrand = async (id) => {

    return await api.delete(`/brands/${id}`);

};