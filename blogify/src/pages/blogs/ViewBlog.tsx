/* eslint-disable @typescript-eslint/no-explicit-any */
import { useParams } from "react-router-dom";
import image from "/assets/icons/login.svg"
import useGet from "@/hooks/useGet";
import { Button } from "@mantine/core";
import { useState } from "react";
import { notifications } from "@mantine/notifications";
import { AuthAPi, getResError } from "@/config/axios.config";
import MainLayout from "@/layouts/MainLayout";
import { useAuth } from "@/contexts/AuthProvider";
import CommentCardComponent from "@/components/coment";

const ViewSingleBlogComponent = () => {
    const { blogId } = useParams();
    const [comment, setComment] = useState("");
    const [commentLoading, setIsCommentLoading] = useState(false);
    const { user } = useAuth();
    const {
        data: blog,
        get,
       
        
    } = useGet<any>(`/blogs/read-blog/${blogId}`, {
        defaultData: [],
    });
    const handleComment = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (comment.trim() == "") {
            notifications.show({
                title: "Comment creation failed",
                message: "Please fill all required fields",
                color: "red",
            });
            return;
        }
        setIsCommentLoading(true);
        console.log("data", comment);
        try {
            const res = await AuthAPi.post("/comments/create-comment", comment, {
                headers: {
                    Authorization: `Bearer ${sessionStorage.getItem('token')}`,
                },
                params: {
                    blogId: blogId,
                }
            });
            console.log(res);
            if (res.data) {
                setComment("");
                notifications.show({
                    title: "Comment Created",
                    message: "Comment created successfully",
                    color: "green",
                });
            }
            get();

        } catch (error) {
            console.log(error);
            notifications.show({
                title: "Comment creation Failed",
                message: getResError(error),
                color: "red",
            });
        }
        setIsCommentLoading(false);
    }

    const handleLogin = () => {
        window.location.href = "/auth/login";
    }
    return (
        <MainLayout>

            <section>
                <div className="w-full md:flex gap-x-8">

                    <div className="h-32 w-32">
                        <img src={image} className="object-cover" />
                    </div>
                    <div className="flex flex-col">
                        <h2 className="text-xl font-bold">{blog?.title}</h2>
                        <p><span className="font-semibold text-sm ">Written By :</span> <span className="
                    text-gray-400 text-sm font-medium">{blog.authorFirstName}{" "}{blog.authorLastName}</span></p>
                        <p><span className="font-semibold text-sm ">Published at :</span><span className="
                    text-gray-400 text-sm font-medium">{new Date(blog?.createdAt?.split('.')[0] + 'Z'.toString()).toLocaleDateString()} {new Date(blog?.createdAt?.split('.')[0] + 'Z'.toString()).toLocaleTimeString()} </span></p>
                        <p><span className="font-semibold text-sm ">Comments :</span><span></span><span className="
                    text-gray-400 text-sm font-medium"> {blog.comments?.length > 1 ? `${blog.comments?.length} comments` : `${blog.comments?.length} comment`} </span></p>

                    </div>
                </div>
                <div className="w-full text-balance  min-h-32 " >{blog.content}</div>
                <div className="md:w-1/4 flex flex-col gap-2 md:mt-10">
                    <form onSubmit={handleComment} className="flex flex-col gap-3">
                        <label className="font-semibold ">Comment: </label>
                        <textarea placeholder="Write your comment here down!" className="p-2 border border-gray-700 rounded outline-none" rows={4} value={comment} onChange={(e) => setComment(e.target.value)} name="comment" id="comment" required />
                        {
                            Object.keys(user!).length > 0 ? (
                                <Button loading={commentLoading} disabled={commentLoading} className="bg-primary " w={"40%"} type="submit">Comment</Button>

                            ) : (
                                <Button onClick={handleLogin} className="bg-primary " w={"40%"} >Login to comment</Button>

                            )
                        }
                    </form>
                </div>
                <p className="font-semibold">Comments: <span>({blog.comments?.length})</span></p>
                {
                    blog.comments && blog.comments?.length > 0 && (

                        <div className="w-full flex flex-col gap-y-2 mt-4">
                            {
                                blog.comments?.map((comment, index) => (
                                    <CommentCardComponent key={index} {...comment} />
                                ))}
                        </div>
                    )}
            </section >
        </MainLayout>
    )


}

export default ViewSingleBlogComponent;