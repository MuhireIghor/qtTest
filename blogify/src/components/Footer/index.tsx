import { useState } from "react";

const FooterComponent = () => {
    const [date, setDate] = useState(new Date().getFullYear());
    return (
        <section className="w-full  border-t-[1px] text-white  flex items-center justify-between bg-primary text-sm px-3">
            <p >&copy;Copy Rights{"  "}{date}</p>
        </section>
    )

}
export default FooterComponent;