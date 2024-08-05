import { useEffect, useState } from "react";
import AuthLayout from "../../layouts/AuthLayout";
import { notifications } from "@mantine/notifications";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import { Link } from "react-router-dom";
import { Button } from "@mantine/core";
import { api, getResError } from "@/config/axios.config";

const LoginPage = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [data, setData] = useState({
        email: "",
        password: "",
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setData({
            email: "",
            password: "",
        })
    }, [])
    const login = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log("Login", data);
        setLoading(true);
        try {
            const res = await api.post("/auth/login", data);
            console.log(res);
            notifications.show({
                title: "Login Success",
                message: "Login Success",
                color: "green",
                autoClose: 3000,
            });
            if (res.data) {
                const user = res.data.data?.user;
                sessionStorage.setItem("token", res.data.data?.accessToken);
                sessionStorage.setItem("user", JSON.stringify(user));
                const nextUrl ="/blogs"
                    
                window.location.href = nextUrl;
            }
        } catch (error) {
            console.log(getResError(error));
            const err = getResError(error);
            notifications.show({
                title: "Login Failed",
                message:
                    err.trim() !== "" ? err :
                        "The Email or password is incorrect",
                color: "red",
                autoClose: 3000,
            });
        }
        setLoading(false);
  
    };
    return (
        <AuthLayout>
            <div className="scale-90 md:w-3/4">
                <h2 className="text-xl text-center font-semibold text-primary">Login</h2>

                <form onSubmit={login} className="">
                    <div className="field flex flex-col gap-2 mt-6">
                        <label
                            htmlFor="email"
                            className="text-md font-regular text-black"
                        >
                            Email Address
                        </label>
                        <input
                            autoFocus={false}
                            value={data.email}
                            onChange={(e) => setData({ ...data, email: e.target.value })}
                            className="outline-none border-none text-black-primary h-[50px] bg-input text-sm px-4 boder border-transparent rounded-[10px] active:border-gray-600"
                            type="text"
                            name=""
                            placeholder="example@gmail.com"
                            id="email"
                        />
                    </div>

                    <div className="field flex flex-col gap-2 mt-6 relative">
                        <label
                            htmlFor="password"
                            className="text-md font-regular text-black-primary"
                        >
                            Password
                        </label>
                        <input
                            autoFocus={false}
                            value={data.password}
                            onChange={(e) =>
                                setData({ ...data, password: e.target.value })
                            }
                            className="outline-none border-none text-black h-[50px] bg-input text-sm px-4 boder rounded-[10px] active:border-gray-600"
                            type={showPassword ? "text" : "password"}
                            name=""
                            placeholder="*********"
                            id="password"
                        />
                        <div className="absolute right-3 bottom-4">
                            {!showPassword ? (
                                <AiFillEyeInvisible
                                    className="text-black cursor-pointer text-xl"
                                    onClick={() => setShowPassword(!showPassword)}
                                />
                            ) : (
                                <AiFillEye
                                    className="text-black-primary cursor-pointer text-xl"
                                    onClick={() => setShowPassword(!showPassword)}
                                />
                            )}
                        </div>
                    </div>

                    <div className="mt-6 flex items-center justify-between">
                        <div className="flex items-center gap-2">
                            <input
                                type="checkbox"
                                className="w-[15px] h-[15px] "

                                name="me"
                                id="me"
                            />
                            <label className="text-sm text-black" htmlFor="me">
                                Remember me
                            </label>
                        </div>
                        <div className="">
                            <Link
                                to="/auth/forgot-password"
                                className="text-sm text-primary"
                            >
                                Reset Password?
                            </Link>
                        </div>
                    </div>

                    <div className="mt-6">
                        <Button
                            type="submit"
                            loading={loading}
                            disabled={loading}
                            radius="md"
                            w={"100%"}
                            size="md"
                            className="h-[50px] bg-primary text-white rounded-[10px] w-full font-semibold"
                        >
                            Log in
                        </Button>
                    </div>

                    <div className="mt-6">
                        <p className="text-md text-black-primary text-center">
                            Don't have account yet?{" "}
                            <a href="/auth/signup" className="text-primary">
                                New Account
                            </a>{" "}
                        </p>
                    </div>
                    <Link
                        to="/on-boarding/fill-profile"
                        className=" text-primary flex justify-center w-full hover:text-cyan-900 duration-300 text-sm mt-3"
                    >
                        Didn't complete profile?
                    </Link>
                </form>
            </div>
        </AuthLayout>
    )

}
export default LoginPage;