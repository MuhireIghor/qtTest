import { FC } from "react";
import { IBlog } from "../../types";
import { useNavigate } from "react-router-dom";

const BlogCardComponent: FC<IBlog> = (props) => {
    const navigate = useNavigate();
    const handleNavigate = () => {
        navigate(`/blogs/${props.id}`);
    }
    return (
        <section onClick={handleNavigate} className="w-11/12 hover:cursor-pointer bg-white shadow-md border rounded-md flex h-32 p-2 mx-auto lg:mx-0">
            {/* Image section */}

            <div className="flex flex-col w-9/12">
                <p className="font-semibold">{props.title}</p>
                <p className="flex gap-x-2"><span className="font-semibold">Written By :</span>{props.authorFirstName}{" "}{props.authorLastName}</p>
                <p className=" text-md font-semibold">Posted On: <span className="text-gray-400 font-medium">{new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleDateString()} {new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleTimeString()}</span></p>

            </div>

        </section>
    )
}
export default BlogCardComponent;