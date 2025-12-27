import { getCodes } from "@/lib/lab-data";
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { vscDarkPlus } from 'react-syntax-highlighter/dist/esm/styles/prism';
import { ArrowLeft, Download, FileCode, Home } from "lucide-react";
import Link from "next/link";
import CopyButton from "@/components/CopyButton";

interface PageProps {
  params: Promise<{
    semester: string;
    subject: string;
    file: string;
  }>;
}

function getLanguageFromExtension(filename: string): string {
  const ext = filename.split('.').pop()?.toLowerCase();
  switch (ext) {
    case 'py': return 'python';
    case 'c': return 'c';
    case 'cpp': return 'cpp';
    case 'java': return 'java';
    case 'js': return 'javascript';
    case 'html': return 'html';
    case 'css': return 'css';
    case 'sh': return 'bash';
    case 'json': return 'json';
    case 'md': return 'markdown';
    default: return 'text';
  }
}

export default async function CodePage({ params }: PageProps) {
  const { semester, subject, file } = await params;
  const fileName = decodeURIComponent(file);
  const subjectName = decodeURIComponent(subject);
  const code = getCodes(semester, subjectName, fileName);
  const language = getLanguageFromExtension(fileName);

  return (
    <div className="min-h-screen bg-[#050505] text-slate-300 font-sans">
      
      {/* --- RESPONSIVE HEADER --- */}
      {/* Reduced padding on mobile (px-4) vs desktop (md:px-6) */}
      <div className="sticky top-0 z-50 flex items-center justify-between px-4 py-3 md:px-6 md:py-4 bg-[#0a0a0a]/90 backdrop-blur-md border-b border-slate-800">
        
        <div className="flex items-center gap-3 md:gap-4 overflow-hidden">
          
          {/* Home Button - Smaller on mobile */}
          <Link
            href="/"
            className="group p-1.5 md:p-2 bg-slate-900 border border-slate-800 rounded-lg hover:border-green-500/50 hover:bg-green-500/10 transition-all flex-shrink-0"
            title="Return to Root"
          >
            <Home className="w-4 h-4 text-slate-500 group-hover:text-green-500 transition-colors" />
          </Link>

          {/* Separator - Hidden on tiny screens */}
          <span className="hidden sm:inline text-slate-700">/</span>

          {/* Back Button */}
          <Link
            href={`/${semester}/${subject}`}
            className="group p-1.5 md:p-2 bg-slate-900 border border-transparent hover:border-slate-800 rounded-lg transition-all text-slate-400 hover:text-green-400 flex-shrink-0"
            title="Back to Subject"
          >
            <ArrowLeft className="w-4 h-4" />
          </Link>

          <div className="hidden sm:block h-6 w-px bg-slate-800 mx-1"></div>

          {/* File Name - Truncates on mobile (max-w-[120px]) */}
          <div className="flex items-center gap-2 md:gap-3 overflow-hidden">
            <span className="text-sm font-bold text-white flex items-center gap-2 truncate max-w-[120px] sm:max-w-none">
              <FileCode className="w-4 h-4 text-green-500 flex-shrink-0" />
              {fileName}
            </span>

            {/* METADATA: HIDDEN ON MOBILE */}
            <span className="hidden sm:inline text-slate-700">•</span>
            <span className="hidden sm:inline text-xs text-slate-500 font-mono flex-shrink-0">
              {code.split('\n').length} lines
            </span>
            <span className="hidden sm:inline text-slate-700">•</span>
            <span className="hidden sm:inline text-xs text-green-600 font-mono uppercase tracking-wider bg-green-900/10 px-2 py-0.5 rounded border border-green-900/30">
              {language}
            </span>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex items-center gap-2 md:gap-3 flex-shrink-0 ml-2">
          <CopyButton code={code} />
          
          <a
            href={`/api/download?file=${semester}/${subjectName}/${fileName}`}
            className="flex items-center gap-2 px-3 py-1.5 text-xs font-bold bg-green-600 text-black rounded hover:bg-green-500 transition-all shadow-[0_0_15px_rgba(34,197,94,0.3)] hover:shadow-[0_0_25px_rgba(34,197,94,0.6)]"
          >
            <Download className="w-4 h-4 md:w-3 md:h-3" />
            <span className="hidden md:inline">DOWNLOAD</span>
          </a>
        </div>
      </div>

      {/* Code Viewer */}
      <div className="max-w-7xl mx-auto p-3 md:p-8">
        <div className="rounded-xl overflow-hidden border border-slate-800 shadow-2xl bg-[#0a0a0a]">
          {/* Added overflow-x-auto wrapper to ensure code scrolls horizontally on mobile */}
          <div className="overflow-x-auto">
             <SyntaxHighlighter
                language={language}
                style={vscDarkPlus}
                customStyle={{
                  margin: 0,
                  padding: '1.5rem',
                  fontSize: '13px', 
                  lineHeight: '1.6',
                  background: '#0a0a0a',
                  minWidth: '100%' 
                }}
                showLineNumbers={true}
                wrapLines={false} 
              >
                {code}
              </SyntaxHighlighter>
          </div>
        </div>
        
        
        <div className="mt-6 text-center text-xs text-slate-600 font-mono sm:hidden">
           {language.toUpperCase()} • {code.split('\n').length} LINES • UTF-8
        </div>
      </div>
    </div>
  );
}