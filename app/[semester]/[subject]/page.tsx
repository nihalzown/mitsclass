import Link from "next/link";
import { getExps } from "@/lib/lab-data";
// 1. IMPORT THE HOME ICON
import { FileCode, ArrowLeft, Terminal, Lock, Home } from "lucide-react";

interface PageProps {
    params: Promise<{
        semester: string;
        subject: string;
    }>;
}

export default async function SubjectPage({ params }: PageProps) {
    const { semester, subject } = await params;

    const decodedSubject = decodeURIComponent(subject);

    // FETCH DATA
    const experiments = getExps(semester, decodedSubject);

    return (
        <div className="min-h-screen p-6 md:p-12 font-sans text-slate-300">

            {/* Header Section */}
            <div className="max-w-4xl mx-auto mb-12 flex items-center gap-3">

                {/* --- NEW HOME BUTTON --- */}
                <Link
                    href="/"
                    className="group p-2 bg-slate-900/50 border border-slate-800 rounded-lg hover:border-green-500/50 hover:bg-green-500/10 transition-all duration-300"
                    title="Return to Root"
                >
                    <Home className="w-4 h-4 text-slate-500 group-hover:text-green-500 transition-colors" />
                </Link>

                {/* Separator Slash */}
                <span className="text-slate-700 font-mono">/</span>

                {/* Back to Semester Button */}
                <Link
                    href={`/${semester}`}
                    className="flex items-center gap-2 px-3 py-1.5 rounded-lg hover:bg-slate-900/50 hover:text-green-400 transition-all text-sm font-mono uppercase tracking-widest border border-transparent hover:border-slate-800"
                >
                    <ArrowLeft className="w-4 h-4" /> {semester}
                </Link>

                {/* Decorative Line */}
                <div className="h-px bg-slate-800 flex-1 ml-2" />

                {/* Path Indicator */}
                <span className="text-slate-500 text-xs font-mono hidden sm:block">
                    DIR: ~/{semester}/{decodedSubject}
                </span>
            </div>

            <div className="max-w-4xl mx-auto">
                <h1 className="text-4xl font-black text-white mb-2 uppercase break-words tracking-tight">
                    {decodedSubject}
                </h1>
                <div className="flex items-center gap-2 text-green-500 mb-10 font-mono text-xs tracking-wider border border-green-900/30 bg-green-900/10 px-3 py-1 rounded w-fit">
                    <Lock className="w-3 h-3" />
                    <span>ACCESS_LEVEL: AUTHORIZED</span>
                </div>

                {/* --- THE SMART CARD LIST --- */}
                <div className="grid gap-3">
                    {experiments.length === 0 ? (
                        <div className="p-6 border border-red-900/50 bg-red-900/10 text-red-400 font-mono text-sm rounded-xl flex items-center gap-3">
                            <div className="w-2 h-2 bg-red-500 rounded-full animate-pulse" />
                            [WARNING]: No protocols found in this sector.
                        </div>
                    ) : (
                        experiments.map((exp) => (
                            <div
                                key={exp.name}
                                className="group relative flex flex-col p-4 bg-slate-900/30 border border-slate-800 rounded-xl hover:border-green-500/50 hover:bg-slate-900/50 transition-all duration-300"
                            >

                                <div className="flex items-center justify-between w-full relative z-10">
                                    <div className="flex items-center gap-4">
                                        <div className="p-2 bg-black/40 rounded-lg border border-slate-800 group-hover:border-green-500/30 transition-colors">
                                            <FileCode className="w-5 h-5 text-slate-600 group-hover:text-green-500 transition-colors" />
                                        </div>
                                        <span className="text-slate-300 font-bold font-mono text-sm group-hover:text-white transition-colors">
                                            {exp.name}
                                        </span>
                                    </div>

                                    <Link
                                        href={`/${semester}/${subject}/${exp.name}`}
                                        className="flex items-center gap-2 px-4 py-2 text-[10px] font-bold bg-green-500/5 text-green-500 border border-green-500/20 rounded hover:bg-green-500 hover:text-black transition-all tracking-wider"
                                    >
                                        <Terminal className="w-3 h-3" />
                                        EXECUTE
                                    </Link>
                                </div>


                                {exp.aim && (
                                    <div
                                        className="grid grid-rows-[0fr] opacity-0 group-hover:grid-rows-[1fr] group-hover:opacity-100 transition-all duration-500 ease-in-out"
                                    >
                                        <div className="overflow-hidden">
                                            <div className="mt-3 pt-3 border-t border-dashed border-slate-800">
                                                <p className="text-sm text-slate-400 font-sans leading-relaxed">
                                                    <span className="text-green-600 font-bold uppercase text-[10px] tracking-wider mr-2 border border-green-900/50 px-1.5 py-0.5 rounded bg-green-900/10">
                                                        AIM
                                                    </span>
                                                    {exp.aim}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                )}

                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
}