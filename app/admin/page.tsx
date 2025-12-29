"use client";

import { useState, useEffect } from "react";
import Link from "next/link";
import { Lock, Activity, Server, FileCode, Users, LogOut, ExternalLink, Download, Percent, RefreshCcw } from "lucide-react";
import { getAllStats } from "@/lib/analytics";

export default function AdminPage() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [password, setPassword] = useState("");
    const [stats, setStats] = useState({ visits: 0, downloads: 0 });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);

    const ADMIN_PASS = "laboombum";

    const handleLogin = (e: React.FormEvent) => {
        e.preventDefault();
        if (password === ADMIN_PASS) {
            setIsAuthenticated(true);
            setError(false);
        } else {
            setError(true);
            setPassword("");
        }
    };

    const fetchStats = async () => {
        setLoading(true);
        const data = await getAllStats();
        setStats(data);
        setLoading(false);
    };

    useEffect(() => {
        if (isAuthenticated) fetchStats();
    }, [isAuthenticated]);

    const engagementRate = stats.visits > 0
        ? ((stats.downloads / stats.visits) * 100).toFixed(1)
        : "0";

    // LOCK SCREEN
    if (!isAuthenticated) {
        return (
            <div className="min-h-screen bg-[#050505] flex flex-col items-center justify-center p-6 text-center font-sans">
                <div className="mb-8 p-6 bg-red-900/10 rounded-full border border-red-500/20 animate-pulse">
                    <Lock className="w-12 h-12 text-red-500" />
                </div>
                <h1 className="text-3xl font-black text-white tracking-tight mb-2">RESTRICTED <span className="text-red-500">SECTOR</span></h1>
                <p className="text-slate-500 font-mono text-sm mb-8 max-w-md">Authentication required. Enter root access code.</p>

                <form onSubmit={handleLogin} className="w-full max-w-sm space-y-4">
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="ENTER PASSCODE" className="w-full bg-black border border-slate-800 focus:border-red-500 rounded-lg px-4 py-3 text-center text-white tracking-[0.5em] font-mono placeholder:tracking-normal placeholder:font-sans placeholder:text-slate-600 outline-none transition-all" autoFocus />
                    {error && <div className="text-red-500 text-xs font-mono font-bold">[ERROR]: INVALID CREDENTIALS</div>}
                    <button type="submit" className="w-full bg-white text-black font-bold py-3 rounded-lg hover:bg-slate-200 transition-colors tracking-widest text-sm">AUTHENTICATE</button>
                </form>
                <Link href="/" className="mt-8 text-xs text-slate-600 hover:text-white transition-colors font-mono">← RETURN TO HOME</Link>
            </div>
        );
    }

    // DASHBOARD
    return (
        <div className="min-h-screen bg-[#050505] text-slate-300 font-sans p-6 md:p-12">
            <div className="max-w-6xl mx-auto flex flex-col md:flex-row items-center justify-between mb-12 gap-6">
                <div>
                    <h1 className="text-4xl font-black text-white tracking-tight">ADMIN <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-400 to-emerald-700">DASHBOARD</span></h1>
                    <p className="text-slate-500 font-mono text-xs mt-2 flex items-center gap-2"><span className="w-2 h-2 rounded-full bg-green-500 animate-pulse" />SYSTEM ONLINE • AUTHORIZED</p>
                </div>
                <div className="flex gap-3">
                    <button onClick={fetchStats} className="p-2 bg-slate-900 text-slate-400 rounded-lg hover:bg-slate-800 hover:text-white transition-colors"><RefreshCcw className={`w-4 h-4 ${loading ? 'animate-spin' : ''}`} /></button>
                    <button onClick={() => setIsAuthenticated(false)} className="flex items-center gap-2 px-4 py-2 bg-red-900/10 text-red-500 border border-red-900/30 rounded-lg hover:bg-red-900/20 transition-all text-xs font-bold tracking-wider"><LogOut className="w-4 h-4" />LOGOUT</button>
                </div>
            </div>

            {/* Analytics Grid */}
            <div className="max-w-6xl mx-auto mb-12">
                <h2 className="text-sm font-bold text-white mb-6 flex items-center gap-2 tracking-widest uppercase"><Activity className="w-4 h-4 text-green-500" />Live Intelligence</h2>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div className="p-6 bg-[#0a0a0a] border border-slate-800 rounded-xl relative overflow-hidden group hover:border-blue-500/30 transition-all">
                        <div className="absolute top-0 right-0 p-4 opacity-5 group-hover:opacity-10 transition-opacity"><Users className="w-24 h-24 text-blue-500" /></div>
                        <div className="text-4xl font-black text-white mb-1 relative z-10">{stats.visits}</div>
                        <div className="text-xs text-slate-500 font-mono uppercase tracking-widest">Total Visits</div>
                    </div>
                    <div className="p-6 bg-[#0a0a0a] border border-slate-800 rounded-xl relative overflow-hidden group hover:border-green-500/30 transition-all">
                        <div className="absolute top-0 right-0 p-4 opacity-5 group-hover:opacity-10 transition-opacity"><Download className="w-24 h-24 text-green-500" /></div>
                        <div className="text-4xl font-black text-white mb-1 relative z-10">{stats.downloads}</div>
                        <div className="text-xs text-slate-500 font-mono uppercase tracking-widest">Total Downloads</div>
                    </div>
                    <div className="p-6 bg-[#0a0a0a] border border-slate-800 rounded-xl relative overflow-hidden group hover:border-purple-500/30 transition-all">
                        <div className="absolute top-0 right-0 p-4 opacity-5 group-hover:opacity-10 transition-opacity"><Percent className="w-24 h-24 text-purple-500" /></div>
                        <div className="text-4xl font-black text-white mb-1 relative z-10">{engagementRate}%</div>
                        <div className="text-xs text-slate-500 font-mono uppercase tracking-widest">Engagement Rate</div>
                    </div>
                </div>
            </div>

            {/* Links Grid */}
            <div className="max-w-6xl mx-auto mb-12">
                <h2 className="text-sm font-bold text-white mb-6 flex items-center gap-2 tracking-widest uppercase"><Server className="w-4 h-4 text-slate-500" />External Controls</h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <Link href="https://vercel.com/dashboard" target="_blank" className="group p-6 bg-[#0a0a0a] border border-slate-800 rounded-xl hover:border-white/20 transition-all flex justify-between items-center">
                        <div>
                            <h3 className="text-lg font-bold text-white">Vercel Dashboard</h3>
                            <p className="text-xs text-slate-500 mt-1">Deployments & Logs</p>
                        </div>
                        <ExternalLink className="w-4 h-4 text-slate-500 group-hover:text-white" />
                    </Link>
                    <Link href="https://github.com/nihalzown" target="_blank" className="group p-6 bg-[#0a0a0a] border border-slate-800 rounded-xl hover:border-white/20 transition-all flex justify-between items-center">
                        <div>
                            <h3 className="text-lg font-bold text-white">GitHub Repo</h3>
                            <p className="text-xs text-slate-500 mt-1">Source Code</p>
                        </div>
                        <ExternalLink className="w-4 h-4 text-slate-500 group-hover:text-white" />
                    </Link>
                </div>
            </div>
        </div>
    );
}