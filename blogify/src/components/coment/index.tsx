import {  AuthAPi, getResError } from "@/config/axios.config";
import { useAuth } from "@/contexts/AuthProvider";
import { ERole } from "@/types/base.type";
import { Button } from "@mantine/core";
import { notifications } from "@mantine/notifications";
import { FC, useState } from "react";

export interface ICommentCardProps {
    id:string;
    authorId:string;
    authorName: string;
    content: string;
    createdAt: string;
}
const CommentCardComponent: FC<ICommentCardProps> = (props) => {
    const { user } = useAuth();
    const [isDeletingComment, setIsDeletingComment] = useState(false);
    console.log("comparing the user", user)
    console.log(props, "the props======")
    const handleDelete = async (commentId: string) => {
        setIsDeletingComment(true);
        try {
            await AuthAPi.delete(`/comments/delete-comment/${commentId}`);
            window.location.reload();
            // setComments(comments.filter((comment) => comment.id!== commentId))

        }
        catch (error) {
            console.log(error);
            notifications.show({
                title: "Comment deletion Failed",
                message: getResError(error),
                color: "red",
            });
        }
        setIsDeletingComment(false);
    }
    return (
        <section className="w-fit flex flex-col gap-y-6 border border-gray-400 rounded-tr-xl p-2">
            <div className="flex gap-x-4 items-center">
                <img
                    className=" rounded-full w-10"
                    src={`https://ui-avatars.com/api/?name=${props?.authorName}}&bold=true`}
                    alt=""
                />                <div >
                    <h3 className="font-semibold">{props.authorName}</h3>
                    <p className=" text-md font-semibold">Commented On: <span className="text-gray-400 font-medium">{new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleDateString()} {new Date(props.createdAt.split('.')[0] + 'Z'.toString()).toLocaleTimeString()}</span></p>
                </div>
            </div>
            <p>{props.content!}</p>
            {
                (user?.role == ERole.PUBLISHER|| user?.id == props?.authorId) && (
                    <Button disabled={isDeletingComment}  loading={isDeletingComment} onClick={()=>handleDelete(props.id)} w={"50%"} color="#605BFF" className=" text-white font-semibold p-2 rounde ">Delete</Button>

                )
            }

        </section>
    )
}
export default CommentCardComponent;