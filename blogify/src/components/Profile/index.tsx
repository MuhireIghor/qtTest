import { FC } from "react";
import { useNavigate } from "react-router-dom";

interface ProfileBoxProps {
    person: {

        userName: string;
        email: string;
    } | null;
}
const ProfileBox: FC<ProfileBoxProps> = (props) => {
    const navigate = useNavigate();
    const handleShowProfile = () => {
        navigate("/account")
    }
    return (
        <div className="flex gap-x-6 items-center  px-3 rounded-md shadow bg-white" onClick={handleShowProfile}>
            <div className="w-8 h-8 flex items-center justify-center text-sm text-black rounded-full bg-gray-200">
                <span className="flex">{`${props.person?.userName.split(".")[0]}${props.person?.userName.split(".")[1].split("")[0]}`}{}</span>
            </div>
            <section className="flex flex-col gap-y-[0.5px] text-sm rouned-lg p-1">

                <p className="font-semibold">{props.person?.userName ?? "MNuhire"}</p>
                <p className="">{props.person?.email ?? "muhireighor123@gmail.com"}</p>
            </section>
        </div>

    )
}
export default ProfileBox;