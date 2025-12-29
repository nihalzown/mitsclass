"use client";

import { useEffect } from "react";
import { trackEvent } from "@/lib/analytics";
import Link from "next/link";
import { getSems } from "@/lib/lab-data";
import { Terminal, Shield, ChevronRight, Cpu, Github, Instagram } from "lucide-react";
import InlineSearch from "@/components/Inlinesearch";

export default function Home() {
  const semesters = getSems();
  useEffect(() => {
    trackEvent("visits");
  }, []);

  return (
    <main className="min-h-screen flex flex-col items-center justify-center p-6 relative">

      {/* Background Glow */}
      <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[500px] h-[500px] bg-green-500/5 rounded-full blur-[100px] pointer-events-none" />

      <div className="relative z-50 text-center max-w-3xl mb-16 space-y-6 w-full">

        <div className="inline-flex items-center gap-2 px-4 py-1.5 rounded-full bg-green-900/20 border border-green-500/30 text-green-400 text-xs font-mono tracking-widest uppercase animate-pulse">
          <span className="w-2 h-2 rounded-full bg-green-500" />
          System Online
        </div>

        <h1 className="text-5xl md:text-7xl font-black tracking-tighter text-white">
          CYBER <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-400 to-emerald-700">VAULT</span>
        </h1>

        <p className="text-lg text-slate-400 max-w-xl mx-auto font-mono">
          Secure Repository for KTU Lab Records. <br />
          Select a sector to initialize.
        </p>

        <div className="mt-8 w-full max-w-lg mx-auto text-left">
          <InlineSearch />
        </div>

      </div>

      <div className="z-10 grid grid-cols-1 md:grid-cols-3 gap-6 w-full max-w-5xl">
        {semesters.length === 0 ? (
          <div className="col-span-3 text-center p-10 border border-dashed border-red-900 bg-red-900/10 text-red-500 font-mono">
            [ERROR]: No content found in vault. Please add 'S3' folder.
          </div>
        ) : (
          semesters.map((sem) => (
            <Link
              key={sem}
              href={`/${sem}`}
              className="group relative p-8 bg-black/40 backdrop-blur-md border border-slate-800 rounded-xl hover:border-green-500/50 transition-all duration-300 hover:-translate-y-1 hover:shadow-[0_0_30px_rgba(34,197,94,0.1)]"
            >
              <div className="flex items-start justify-between mb-8">
                <div className="p-3 bg-slate-900 rounded-lg group-hover:bg-green-500/10 group-hover:text-green-400 transition-colors">
                  <Shield className="w-6 h-6" />
                </div>
                <span className="text-xs font-bold text-slate-600 uppercase tracking-widest group-hover:text-green-500 transition-colors font-mono">
                  [DIR]
                </span>
              </div>

              <div>
                <h2 className="text-3xl font-bold text-white mb-2 group-hover:text-green-400 transition-colors">
                  {sem}
                </h2>
                <p className="text-slate-500 text-sm mb-6 font-mono">
                  /root/{sem.toLowerCase()}
                </p>

                <div className="flex items-center text-sm font-bold text-slate-400 group-hover:text-green-400 transition-colors">
                  ACCESS <ChevronRight className="w-4 h-4 ml-1 group-hover:translate-x-1 transition-transform" />
                </div>
              </div>
            </Link>
          ))
        )}
      </div>

      {/* Footer */}
      <div className="mt-24 relative z-10 flex flex-col items-center gap-5 pb-8">
        <Link
          href="https://ac-inc.in/"
          target="_blank"
          className="flex items-center gap-2 px-5 py-2 rounded-full bg-green-900/5 border border-green-500/10 hover:border-green-500/30 text-green-600 hover:text-green-400 text-[10px] font-bold tracking-widest uppercase transition-all hover:bg-green-900/10"
        >
          <div className="w-1.5 h-1.5 rounded-full bg-green-500 animate-pulse" />
          Powered by AC Inc.
        </Link>

        <div className="flex items-center gap-4 px-6 py-3 rounded-full border border-slate-800 bg-[#0a0a0a]/80 hover:border-slate-600 transition-all backdrop-blur-md shadow-2xl">
          <span className="text-[10px] md:text-xs font-mono text-slate-500">
            DEV: <span className="text-slate-300 font-bold">NIHALZOWN</span>
          </span>
          <div className="w-px h-3 bg-slate-700" />

          <div className="flex items-center gap-3">
            <Link href="https://github.com/nihalzown" target="_blank" className="group">
              <Github className="w-4 h-4 text-slate-500 group-hover:text-white transition-colors" />
            </Link>
            <Link href="https://www.instagram.com/nihalzown/" target="_blank" className="group">
              <Instagram className="w-4 h-4 text-slate-500 group-hover:text-pink-500 transition-colors" />
            </Link>
          </div>
        </div>
      </div>
    </main>
  );
}