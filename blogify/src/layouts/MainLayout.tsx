import { FC } from "react";
import NavbarComponent from "../components/Navbar";
import FooterComponent from "../components/Footer";

interface MainLayoutProps {
    children: React.ReactNode;
}
const MainLayout: FC<MainLayoutProps> = (props) => {
    return (
        <main className="h-screen relative bg-[#FAFAFB]">
            <div className="w-full h-[12%] ">
                <NavbarComponent />
            </div>
            <div className="h-[80%] mt-4 overflow-y-scroll px-4 mx-auto">
                {props.children}
            </div>
            <div className="w-full h-[5%] flex  bottom-0">
                <FooterComponent />
            </div>
        </main>
    )

}
export default MainLayout;