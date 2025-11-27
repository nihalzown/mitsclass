import Link from "next/link";
import { getSubs } from "@/lib/lab-data";
import { FolderGit2, ArrowLeft,Terminal } from "lucide-react";

interface PageProps{
    params: Promise<{semester: string}>;
}

export default async function SemesterPage({params}: PageProps){
    const { semester } = await params;
    const subjects = getSubs(semester);
    return(
        <div className="min-h-screen p-6 md:p-12 font-sans text-slate-300">
            <div className="max-w-5xl mx-auto mb-12 flex items-center gap-4">
                <Link
                    href="/"
                    className="flex items-center gap-2 hover:text-green-400 transition-colors text-sm font-mono uppercase tracking-widest">
                    <ArrowLeft className="w-4 h-4" /> Return to Root
                </Link>
                <div className="h-px bg-slate-800 flex-1" />
                <span className="text-slate-500 text-xs font-mono">DIR: /home/{semester}</span>
            </div>

            <div className="max-w-5xl mx-auto">
                <h1 className="text-5xl font-black text-white mb-2 uppercase tracking-tighter">
                    {semester} <span className="text-green-500">subjects</span>
                </h1>
            </div>
        </div>

        
    );
}