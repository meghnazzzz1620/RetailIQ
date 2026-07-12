import api from "./api";

export const getDashboardStats = async () => {

    const response = await api.get("/dashboard");

    return response.data;

};

export const getMonthlySales = async () => {

    const response = await api.get("/dashboard/monthly-sales");

    return response.data;

};
export const getRecentOrders = async () => {

    const response = await api.get("/dashboard/recent-orders");

    return response.data;

};

export const getLowStockProducts = async () => {

    const response = await api.get("/dashboard/low-stock");

    return response.data;

};

export const getTopSellingProducts = async () => {

    const response = await api.get("/dashboard/top-products");

    return response.data;

};
export const getWeeklyOrders = async () => {

    const response = await api.get("/dashboard/weekly-orders");

    return response.data;

};

export const getWarehouseInventory = async () => {

    const response = await api.get("/dashboard/warehouse-inventory");

    return response.data;

};