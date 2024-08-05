import { useAuth } from "@/contexts/AuthProvider";
import { CtaButton } from "../common/ui/Button";
import ProfileBox from "../Profile";
import { useEffect, useState } from "react";
import { IUser } from "@/types/user.type";
import { ERole } from "@/types/base.type";

const NavbarComponent = () => {
    const [profile, setProfile] = useState<Partial<IUser>>({
        firstName: "",
        lastName: "",
        email: "",
        role: "COMMENTER"
    })
    const { user } = useAuth();
    useEffect(() => {
        setProfile({
            firstName: user?.firstName,
            lastName: user?.lastName,
            email: user?.email,
            role: user?.role

        });
    }, [user])
    console.log("here is the user", user);
    const handleLogin = () => {
        window.location.href = "/auth/login";
    }
    const handleCreateBlog = () => {
        window.location.href = "/blogs/create";
    }


    return (
        <section className="w-full bg-primary p-2 border-b-2 h-full">
            <div className="w-full flex justify-between items-center">
                <a href="/blogs">
                    <p className="font-montserrat text-white text-xl font-bold">Bloggify</p>
                </a>
                {Object.keys(user!).length != 0 ? (
                    <div className="flex gap-x-4 items-center">
                        {
                            user?.role == ERole.PUBLISHER && (

                                <CtaButton onClick={handleCreateBlog} text="Create" />
                            )
                        }
                        <ProfileBox person={{ userName: `${profile.firstName?.charAt(0)}.${profile.lastName}`, email: profile.email! }} />
                    </div>

                ) : (
                    <CtaButton onClick={handleLogin} text="Login"  />
                )}
            </div>
        </section>
    )

}
export default NavbarComponent;