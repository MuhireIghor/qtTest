//The nothing found view to render when no data is found for table data

export default function EmptyView({
    message,
    minHeight,
    buttonLabel,
    buttonAction,
 }: {
    message: string;
    minHeight?: string;
    buttonLabel?: string;
    buttonAction?: () => void;
 }) {
    return (
       <div className="w-full">
          <div
             className={`${
                minHeight ? minHeight : 'min-h-[60vh]'
             } bg-gray-50 rounded-lg mt-6 py-6 flex flex-col items-center justify-center gap-6`}
          >
             <img src={'/assets/icons/empty.svg'} width={300} height={300} alt="empty" />
             <h1 className="text-xl font-semibold text-gray-700">{message}</h1>
             {buttonLabel && buttonAction && (
                <button onClick={buttonAction} className="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
                   {buttonLabel}
                </button>
             )}
          </div>
       </div>
    );
 }