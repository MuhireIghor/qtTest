import axios from "axios";

export const baseUrl = import.meta.env.VITE_API_URL as string;

export const api = axios.create({
  baseURL:
    (import.meta.env.VITE_API_URL as string) ??
    "http://192.168.0.101:9090/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});
export const AuthAPi = axios.create({
  baseURL:
    (import.meta.env.VITE_API_URL as string) ??
    "http://192.168.0.101:9090/api/v1",
  headers: {
    Authorization: `Bearer ${sessionStorage.getItem("token")}`,
    "Content-Type": "application/json",
  }
});

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const getResError = (error?: any) => {
  if (!error) return "Something Went Wrong";
  const isNetError = error?.message?.includes("Network Error");
  if (isNetError) return "Network Error";
  return (
    error?.response?.data?.error ??
    error?.response?.data?.message ??
    error?.message ??
    "Something Went Wrong"
  );
};

