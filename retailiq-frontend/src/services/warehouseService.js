import api from "./api";

export const getAllWarehouses = async () => {

    const response = await api.get("/warehouses");

    return response.data;

};

export const getWarehouseById = async (id) => {

    const response = await api.get(`/warehouses/${id}`);

    return response.data;

};

export const createWarehouse = async (warehouse) => {

    const response = await api.post("/warehouses", warehouse);

    return response.data;

};

export const updateWarehouse = async (id, warehouse) => {

    const response = await api.put(`/warehouses/${id}`, warehouse);

    return response.data;

};

export const deleteWarehouse = async (id) => {

    return await api.delete(`/warehouses/${id}`);

};