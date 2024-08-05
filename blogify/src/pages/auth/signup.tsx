import { useEffect, useState } from "react";
import AuthLayout from "../../layouts/AuthLayout";
import { notifications } from "@mantine/notifications";
import { AiFillEye, AiFillEyeInvisible } from "react-icons/ai";
import { Button } from "@mantine/core";
import { IUserDto } from "@/types/user.type";
import { api, getResError } from "@/config/axios.config";
import { ERole } from "@/types/base.type";

const SignupPage = () => {
    const [role, setRole] = useState<ERole|null|string>(ERole.READER);
    const [showPassword, setShowPassword] = useState(false);
    const [data, setData] = useState<IUserDto>({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        setData({
            firstName: "",
            lastName: "",
            email: "",
            password: "",
        });
        setRole(null);
    }, [])
    const signup = async (e: React.ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (data.firstName.trim() == "" || data.lastName.trim() == "" || data.email.trim() == "" || data.password.trim() == "") {
            notifications.show({
                title: "Error",
                message: "Please fill all fields",
                color: "red",
                autoClose: 3000,
            });
            return;
        }
        setLoading(true);
        try {

            const res = await api.post(role == ERole.READER.toString() ? "/users/register/as-reader" : "/users/register/as-publisher", data);
            console.log(res);
            notifications.show({
                title: "Account created ",
                message: "Account created Successfully",
                color: "green",
                autoClose: 3000,
            });
            window.location.href = "/"

        } catch (error) {
            console.log(getResError(error));
            const err = getResError(error);
            notifications.show({
                title: "Login Failed",
                message:
                    err.trim() !== "" ? err :
                        "Please try again!",
                color: "red",
                autoClose: 3000,
            });
        }
        setLoading(false);
    };
    return (
        <AuthLayout>
            <div className="scale-90 w-3/4">

                <h2 className="text-xl text-center font-semibold text-primary">Create Account</h2>
                <form onSubmit={signup} className="">
                    <div className="field flex flex-col gap-2 mt-1">
                        <label
                            htmlFor="firstname"
                            className="text-md font-regular text-black"
                        >
                            First Name
                        </label>
                        <input
                            autoFocus={false}
                            value={data.firstName}
                            defaultValue={""}
                            onChange={(e) => setData({ ...data, firstName: e.target.value })}
                            className="outline-none border-none text-black-primary h-[50px] bg-input text-sm px-4 boder border-transparent rounded-[10px] active:border-gray-600"
                            type="text"
                            name=""
                            placeholder="John"
                            id="firstname"
                        />
                    </div>
                    <div className="field flex flex-col gap-2 mt-1">
                        <label
                            htmlFor="lastname"
                            className="text-md font-regular text-black"
                        >
                            Last Name
                        </label>
                        <input
                            autoFocus={false}
                            value={data.lastName}
                            defaultValue={""}
                            onChange={(e) => setData({ ...data, lastName: e.target.value })}
                            className="outline-none border-none text-black-primary h-[50px] bg-input text-sm px-4 boder border-transparent rounded-[10px] active:border-gray-600"
                            type="text"
                            name=""
                            placeholder="Doe"
                            id="lastname"
                        />
                    </div>
                    <div className="field flex flex-col gap-2 mt-1">
                        <label
                            htmlFor="role"
                            className="text-md font-regular text-black"
                        >
                            Role
                        </label>
                        <select
                            className="outline-none border-none text-black-primary h-[50px] bg-input text-sm px-4 boder border-transparent rounded-[10px] active:border-gray-600"

                            value={role?.toString()} onChange={(e) => setRole(e.target.value )}>
                            <option value="null">Select Role</option>
                            <option value={ERole.PUBLISHER.toString()}>Editor</option>
                            <option value={ERole.READER.toString()}>Reader</option>
                        </select>

                    </div>

                    <div className="field flex flex-col gap-2 mt-1">
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

                    <div className="field flex flex-col gap-2 mt-1 relative">
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



                    <div className="mt-3">
                        <Button
                            type="submit"
                            loading={loading}
                            disabled={loading}
                            radius="md"
                            w={"100%"}
                            size="md"
                            className="h-[50px] bg-primary text-white rounded-[10px] w-full font-semibold"
                        >
                            Sign Up                        </Button>
                    </div>

                    <div className="mt-3">
                        <p className="text-md text-black-primary text-center">
                            Already have account?{" "}
                            <a href="/auth/login" className="text-primary">
                                Login to your account
                            </a>{" "}
                        </p>
                    </div>

                </form>
            </div>
        </AuthLayout>
    )

}
export default SignupPage;