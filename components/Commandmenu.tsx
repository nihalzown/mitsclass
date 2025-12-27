"use client";

import { useEffect, useState } from "react";
import { Command } from "cmdk";
import { Search, FileCode, CornerDownLeft, Command as CommandIcon } from "lucide-react";
import { useRouter } from "next/navigation";

type SearchResult = {
    id: string;
    title: string;
    subtitle: string;
    url: string;
    semester: string;
    subject: string;
};

export default function CommandMenu() {
    const [open, setOpen] = useState(false);
    const [items, setItems] = useState<SearchResult[]>([]);
    const router = useRouter();

    useEffect(() => {
        const down = (e: KeyboardEvent) => {
            if (e.key === "k" && (e.metaKey || e.ctrlKey)) {
                e.preventDefault();
                setOpen((open) => !open);
            }
        };
        document.addEventListener("keydown", down);

        fetch("/api/search")
            .then((res) => res.json())
            .then((data) => setItems(data));

        return () => document.removeEventListener("keydown", down);
    }, []);

    return (
        <Command.Dialog
            open={open}
            onOpenChange={setOpen}
            label="Global Search"
            className="fixed inset-0 z-[100] bg-black/60 backdrop-blur-sm flex items-start justify-center pt-[15vh] px-4"
            onClick={(e) => {
                if (e.target === e.currentTarget) setOpen(false);
            }}
        >
            {/* Container: Glassmorphism + subtle border */}
            <div className="w-full max-w-2xl bg-[#0a0a0a]/80 backdrop-blur-2xl border border-white/10 rounded-xl shadow-2xl overflow-hidden animate-in fade-in zoom-in-95 slide-in-from-top-2 duration-200">

                {/* Header: Clean & Minimal */}
                <div className="flex items-center border-b border-white/5 px-4 py-3">
                    <Search className="w-4 h-4 text-slate-500 mr-3" />
                    <Command.Input
                        placeholder="Search experiments..."
                        className="w-full bg-transparent text-[15px] text-white placeholder:text-slate-500 focus:outline-none font-sans font-medium"
                    />
                    <div className="hidden md:flex items-center gap-1.5">
                        <span className="text-[10px] font-medium text-slate-500 border border-white/10 bg-white/5 px-1.5 py-0.5 rounded">ESC</span>
                    </div>
                </div>

                {/* Results List */}
                <Command.List className="max-h-[60vh] overflow-y-auto p-2 scrollbar-hide">
                    <Command.Empty className="py-10 text-center text-slate-500 text-sm">
                        No results found.
                    </Command.Empty>

                    {items.map((item) => (
                        <Command.Item
                            key={item.id}
                            value={`${item.title} ${item.subtitle} ${item.subject}`}
                            onSelect={() => {
                                setOpen(false);
                                router.push(item.url);
                            }}
                            // Modern Hover State: Subtle light background, green accent
                            className="group flex items-center justify-between p-3 mb-1 rounded-lg cursor-pointer aria-selected:bg-white/5 aria-selected:text-white text-slate-400 transition-all duration-150"
                        >
                            <div className="flex items-center gap-3">
                                {/* Icon: Minimalist Box */}
                                <div className="flex items-center justify-center w-8 h-8 rounded-md bg-white/5 border border-white/5 group-aria-selected:border-green-500/30 group-aria-selected:bg-green-500/10 transition-colors">
                                    <FileCode className="w-4 h-4 text-slate-500 group-aria-selected:text-green-400" />
                                </div>

                                <div className="flex flex-col">
                                    {/* Title: Clean Sans-Serif */}
                                    <span className="text-sm font-medium text-slate-200 group-aria-selected:text-white">
                                        {item.title}
                                    </span>
                                    {/* Subtitle: Truncated */}
                                    <span className="text-[11px] text-slate-500 line-clamp-1 group-aria-selected:text-slate-400">
                                        {item.subtitle}
                                    </span>
                                </div>
                            </div>

                            {/* Right Side: The "Pill" Badge */}
                            <div className="flex items-center gap-3">
                                <span className="hidden sm:inline-flex items-center px-2 py-0.5 rounded text-[10px] font-medium uppercase tracking-wider bg-white/5 border border-white/5 text-slate-500 group-aria-selected:text-slate-300 group-aria-selected:border-white/10">
                                    {item.semester} / {item.subject}
                                </span>

                                {/* Enter Icon: Appears on hover */}
                                <CornerDownLeft className="w-3.5 h-3.5 text-slate-600 opacity-0 group-aria-selected:opacity-100 transition-opacity" />
                            </div>
                        </Command.Item>
                    ))}
                </Command.List>

                {/* Footer: Ultra Minimal */}
                <div className="px-4 py-2 border-t border-white/5 bg-black/20 flex justify-between items-center">
                    <span className="text-[10px] text-slate-600 font-medium">{items.length} records</span>
                    <div className="flex items-center gap-3 text-[10px] text-slate-600">
                        <span className="flex items-center gap-1">
                            <CommandIcon className="w-3 h-3" /> Select
                        </span>
                    </div>
                </div>
            </div>
        </Command.Dialog>
    );
}