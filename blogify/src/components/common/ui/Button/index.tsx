/* eslint-disable @typescript-eslint/no-explicit-any */
import { FC } from "react"

interface ButtonProps {
    text: string;
    onClick:()=>any

}
export const CtaButton: FC<ButtonProps> = (props) => {
    const handleCreateBlog = () => {
        window.location.href = "/blogs/create"
    }
    return (
        <button onClick={handleCreateBlog} className="bg-white text-primary h-8  px-4 rounded border border-primary flex items-center justify-center hover:cursoor-pointer duration-400 hover:bg-primary hover:text-white" >

            {props.text}
        </button>
    )
}