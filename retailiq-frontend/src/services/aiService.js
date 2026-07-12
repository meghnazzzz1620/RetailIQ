import api from "./api";

export const askAI = async (question) => {

    const response = await api.post("/ai/ask", {
        question,
    });

    return response.data;

};