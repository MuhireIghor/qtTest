import { FC, useEffect, useState } from "react"
import { useLocation } from "react-router-dom"
import loginIllustration from "/assets/icons/login.svg";
import signupIllustration from "/assets/icons/signup.svg";
interface AuthLayoutProps {
    children: React.ReactNode
}
const AuthLayout: FC<AuthLayoutProps> = (props) => {
    const [isLoginRoute, setIsLoginRoute] = useState(false);
    const location = useLocation();
    useEffect(() => {
        location.pathname.toString().split("/").includes("login") || location.pathname.toString() == "/" ? setIsLoginRoute(true) : setIsLoginRoute(false);
    }, [location])





    return (
        <section className="bg-white w-full h-screen flex items-center justify-center">
            <div className="w-3/4 flex items-center shadow-xl border rounded-lg">
            {/* Illustration section */}
            <div className="basis-1/2 h-full">
                {
                    isLoginRoute ? (
                        <div className="w-[70%] flex justify-center h-full scale-30">
                            <img className="w-full h-full " src={loginIllustration} />
                        </div>
                    ) : (
                        <div className="w-[70%] flex justify-center h-full">
                            <img className="w-full h-full " src={signupIllustration} />
                        </div>
                    )
                }
            </div>
            <div className="basis-1/2 ">
            {props.children}
            </div>

            </div>
                
        </section>
    )
}
export default AuthLayout;