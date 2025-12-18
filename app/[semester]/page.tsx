import Link from "next/link";
import { getSubs } from "@/lib/lab-data";
import { FolderGit2, ArrowLeft, Terminal } from "lucide-react";

interface PageProps {
    params: Promise<{ semester: string }>;
}

export default async function SemesterPage({ params }: PageProps) {
    const { semester } = await params;
    const subjects = getSubs(semester);
    return (
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
                <p className="text-slate-400 mb-10 text-lg">
                    Select a target system to decrypt.
                </p>
                <div className="grid gap-4">
                    {subjects.length === 0 ? (
                        <div className="p-6 border border-red-900/50 bg-red-900/10 text-red-400 rounded-lg font-mono">
                            [ERROR]: No subjects found in {semester}. Please check your content folder.
                        </div>
                    ) : (
                        subjects.map((subject) => (
                            <Link
                                key={subject}
                                href={`/${semester}/${subject}`}
                                className="group flex items-center justify-between p-6 bg-slate-900/40 backdrop-blur border border-slate-800 rounded-xl hover:border-green-500/50 hover:bg-slate-900/60 transition-all duration-300"
                            >
                                <div className="flex items-center gap-5">
                                    <div className="p-3 bg-black border border-slate-800 rounded-lg group-hover:border-green-500/50 transition-colors">
                                        <FolderGit2 className="w-6 h-6 text-green-600 group-hover:text-green-400" />
                                    </div>
                                    <div>
                                        <h2 className="text-xl font-bold text-white group-hover:text-green-400 transition-colors">
                                            {subject}
                                        </h2>
                                        <span className="text-xs font-mono text-slate-500 group-hover:text-green-500/50">
                                            ACCESS_LEVEL: GRANTED
                                        </span>
                                    </div>
                                </div>

                                <Terminal className="w-5 h-5 text-slate-700 group-hover:text-green-500 transform group-hover:translate-x-1 transition-all" />
                            </Link>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
}