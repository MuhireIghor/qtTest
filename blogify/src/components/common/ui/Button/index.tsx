import { FC } from "react"

interface ButtonProps  {
    text: string;

}
export const CtaButton: FC<ButtonProps> = (props) => {
    return (
        <button className="bg-white text-primary h-8  px-4 rounded border border-primary flex items-center justify-center hover:cursoor-pointer duration-400 hover:bg-primary hover:text-white">

            {props.text}
        </button>
    )
}