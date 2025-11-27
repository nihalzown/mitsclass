import Link from "next/link";
import { getExps } from "@/lib/lab-data";
import {FileCode, ArrowLeft, Terminal, Lock} from "lucide-react";

interface PageProps {
  params: Promise<{
    semester: string;
    subject: string;
  }>;
}

export default async function SubjectPage({ params }: PageProps){
    const { semester, subject } = await params;
    const decodedSubject = decodeURIComponent(subject);
    const experiments = getExps(semester, decodedSubject);

    return(
        <div className="min-h-screen p-6 md:p-12 font-sans text-slate-300">
            <div className="max-w-4xl mx-auto mb-12 flex items-center gap-4">
                <Link
                    href={`/${semester}`}
                    className="flex items-center gap-2 hover:text-green-400 transition-colors text-sm font-mono uppercase tracking-widest"
                >
                    <ArrowLeft className="w-4 h-4" /> ../{semester}
                </Link>
                <div className="h-px bg-slate-800 flex-1" />
                <span className="text-slate-500 text-xs font-mono">DIR: /{semester}/{decodedSubject}</span>
            </div>

            <div className="max-w-4xl mx-auto">
                <h1 className="text-4xl font-black text-white mb-2 uppercase break-words">
                    {decodedSubject}
                </h1>
                <div className="flex items-center gap-2 text-green-500 mb-10 font-mono text-sm">
                    <Lock className="w-4 h-4" />
                    <span>SOURCE_CODE_ACCESS: AUTHORIZED</span>
                </div>


                <div className="grid gap-3">
                    {experiments.length === 0 ? (
                        <div className="p-4 border border-yellow-900/50 bg-yellow-900/10 text-yellow-500 font-mono text-sm">
                            [WARNING]: No files found in this directory.
                         </div>
                     ) : (
                        experiments.map((exp) => (
                            <div 
                                key={exp.name}
                                className="group flex items-center justify-between p-4 bg-slate-900/30 border border-slate-800 rounded-lg hover:border-green-500/50 hover:bg-slate-900/50 transition-all"
                            >
                                {<div className="flex items-center gap-4">
                                    <FileCode className="w-5 h-5 text-slate-600 group-hover:text-green-500 transition-colors" />
                                    <span className="text-slate-300 font-medium font-mono text-sm group-hover:text-white transition-colors">
                                        {exp.name}
                                    </span>
                                </div>}

                                <div className="flex gap-3">
                                <Link
                                    href={`/${semester}/${subject}/${exp.name}`}
                                    className="flex items-center gap-2 px-4 py-2 text-xs font-bold bg-green-900/10 text-green-500 border border-green-900/50 rounded hover:bg-green-500 hover:text-black hover:border-green-400 transition-all"
                                >
                                    <Terminal className="w-3 h-3" />
                                    EXECUTE
                                </Link>
                                </div>
                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    )
}