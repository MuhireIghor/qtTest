import React from "react";
import { useAuth } from "@/contexts/AuthProvider";
import { Input, InputWrapper } from "@mantine/core";
import { FaArrowRightFromBracket } from "react-icons/fa6";

const AccountPage = () => {
    const { user } = useAuth();
    const handleLogout = () => {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("user");
        window.location.href = "/";
    }

    return (
        <div className="w-full h-full flex p-2 flex-col items-center bg-[#FAFAFB]">
            <div className=" border-gray-400 border-2 rounded-full ">
                <img
                    className=" rounded-full w-40"
                    src={`https://ui-avatars.com/api/?name=${user?.firstName}+${user?.email}&bold=true`}
                    alt=""
                />
            </div>
            <div className="flex flex-col w-full items-center mt-5 mx-auto max-w-[800px] gap-y-4">
                <div className="grid w-full gap-x-6 gap-y-3 sm:grid-cols-2 ">
                    <InputWrapper label="First Name" >
                        <Input  value={user?.firstName} disabled type={"text"} />
                    </InputWrapper>
                    <InputWrapper label="Last Name" >
                        <Input  value={user?.lastName} disabled type={"text"} />
                    </InputWrapper>
                    <InputWrapper label="Email"  >
                        <Input  value={user?.email} disabled type={"text"} />
                    </InputWrapper>

                    <InputWrapper label="Role" >
                        <Input  value={user?.role.toLocaleLowerCase()} disabled type={"text"} />
                    </InputWrapper>
                </div>
                <button className="bg-red-500 text-white font-semibold flex items-center gap-x-3 text-center px-4 py-2 rounded-lg border border-red-500" onClick={handleLogout}>
                    <FaArrowRightFromBracket className="rotate-180" />
                    <span>Log out</span></button>
            </div>
        </div>
    );
};

export default AccountPage;
