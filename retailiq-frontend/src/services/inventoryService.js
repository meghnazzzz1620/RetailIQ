import api from "./api";

export const getAllInventory = async () => {

    const response = await api.get("/inventory");

    return response.data;

};

export const getInventoryById = async (id) => {

    const response = await api.get(`/inventory/${id}`);

    return response.data;

};

export const createInventory = async (inventory) => {

    const response = await api.post("/inventory", inventory);

    return response.data;

};

export const updateInventory = async (id, inventory) => {

    const response = await api.put(`/inventory/${id}`, inventory);

    return response.data;

};

export const deleteInventory = async (id) => {

    return await api.delete(`/inventory/${id}`);

};