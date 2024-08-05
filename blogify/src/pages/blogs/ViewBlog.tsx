import { useParams } from "react-router-dom";
import image from "/assets/icons/login.svg"

const ViewSingleBlogComponent = () => {
    const { blogId } = useParams();
    return (
        <section>
            <div className="w-full flex gap-x-8">

                <div className="h-52 w-52">
                    <img src={image} className="object-cover" />
                </div>
                <div className="flex flex-col">
                    <p>{}</p>
                </div>
            </div>
            <p>{blogId}</p>
        </section>
    )


}

export default ViewSingleBlogComponent;