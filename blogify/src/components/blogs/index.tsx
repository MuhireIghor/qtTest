import { FC } from "react";
import { IBlog } from "../../types";
import { useNavigate } from "react-router-dom";

const BlogCardComponent: FC<IBlog> = (props) => {
    const navigate = useNavigate();
    const handleNavigate = () => {
        navigate(`/blogs/${props.title}`);
    }
    return (
        <section onClick={handleNavigate} className="w-11/12 hover:cursor-pointer bg-white shadow-md border rounded-md flex h-32 p-2">
            {/* Image section */}
            <div className="w-2/12">
                <img src="" alt="Blog Covevr Image" />
            </div>
            <div className="flex flex-col w-9/12">
                <p className="font-semibold">{props.title}</p>
                <p>Written By {props.author}</p>
                <p className="text-gray-400 text-md font-medium">Posted On: {props.createdAt}</p>

            </div>

        </section>
    )
}
export default BlogCardComponent;