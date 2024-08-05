import { FC } from "react";
import { IBlog } from "../../types";
import { useNavigate } from "react-router-dom";

const BlogCardComponent: FC<IBlog> = (props) => {
    const navigate = useNavigate();
    const handleNavigate = () => {
        navigate(`/blogs/${props.id}`);
    }
    return (
        <section onClick={handleNavigate} className="bg-white rounded-lg shadow-md p-6 border border-transparent hover:border-blue-100 cursor-pointer">
            {/* Image section */}

            <div className="flex flex-col w-9/12">
                <p className="font-semibold text-2xl">{props.title}</p>
                <p className="flex gap-x-2 p-2"><span className="font-semibold">Written By :</span><span className="text-gray-400 font-medium">{props.authorFirstName}{" "}{props.authorLastName}</span></p>
                <p className="text-gray-600 mt-2 bg-gray-50 p-3 mb-4">{props.content?.toString().substring(0,100)+"..."}</p>
                <p className=" text-md font-semibold p-2">Posted On: <span className="text-gray-400 font-medium">{new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleDateString()} {new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleTimeString()}</span></p>
            </div>

        </section>
    )
}
export default BlogCardComponent;