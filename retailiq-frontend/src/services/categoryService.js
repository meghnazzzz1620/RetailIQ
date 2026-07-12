import api from "./api";

export const getAllCategories = async () => {

    const response = await api.get("/categories");

    return response.data;

};

export const getCategoryById = async (id) => {

    const response = await api.get(`/categories/${id}`);

    return response.data;

};

export const createCategory = async (category) => {

    const response = await api.post("/categories", category);

    return response.data;

};

export const updateCategory = async (id, category) => {

    const response = await api.put(`/categories/${id}`, category);

    return response.data;

};

export const deleteCategory = async (id) => {

    return await api.delete(`/categories/${id}`);

};