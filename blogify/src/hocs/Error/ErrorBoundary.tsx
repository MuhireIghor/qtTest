/* eslint-disable @typescript-eslint/no-explicit-any */
import React from 'react';
import { MdErrorOutline } from 'react-icons/md';
//Added the error boundary component to handle errors in the application
class ErrorBoundary extends React.Component<
   { children: React.ReactNode },
   {
      hasError: boolean;
      handleReload: () => void;
      handleGoBack: () => void;
   }
> {
   constructor(props: any) {
      super(props);
      this.state = {
         hasError: false,
         handleReload: () => {
            window.location.reload();
         },
         handleGoBack: () => {
            window.history.back();
         },
      };
   }
   // eslint-disable-next-line @typescript-eslint/no-unused-vars
   static getDerivedStateFromError(error: Error) {
      return { hasError: true };
   }
   componentDidCatch(error: Error, errorInfo: any) {
      console.log(error, errorInfo);
   }
   static handleReload() {
      window.location.reload();
   }
   render() {
      if (this.state.hasError) {
         return (
            <div className="flex items-center justify-center min-h-screen">
               <div className="flex flex-col gap-4 items-center">
                  {/* <Logo /> */}
                  <div className="flex items-center gap-2 w-fit">
                     <i className="text-red-500">
                        <MdErrorOutline size={30} />
                     </i>
                     <h1 className="text-4xl font-bold">An error occured </h1>
                  </div>
                  <p>
                     This action didn't go as planned, contact the{' '}
                     <a className="text-[#5D6E8B] underline underline-offset-8 cursor-pointer">development team</a> for support
                  </p>
                  <div className="flex items-center gap-2 w-fit">
                     <button className="bg-[#5D6E8B] text-white rounded-full w-fit px-6 py-2" onClick={this.state.handleReload}>
                        Reload
                     </button>
                     <button className="text-[#5D6E8B]  bg-white rounded-full w-fit px-6 py-2" onClick={this.state.handleGoBack}>
                        Go back
                     </button>
                  </div>
               </div>
            </div>
         );
      }
      return this.props.children;
   }
}
export default ErrorBoundary;