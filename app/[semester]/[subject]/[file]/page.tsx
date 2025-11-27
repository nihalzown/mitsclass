import { getCodes } from "@/lib/lab-data";
import { Prism as SyntaxHighlighter } from 'react-syntax-highlighter';
import { vscDarkPlus } from 'react-syntax-highlighter/dist/esm/styles/prism';
import { ArrowLeft, Download, FileCode } from "lucide-react";
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
    case 'jsx': return 'jsx';
    case 'ts': return 'typescript';
    case 'tsx': return 'tsx';
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
      <div className="sticky top-0 z-50 flex items-center justify-between px-6 py-4 bg-[#0a0a0a]/80 backdrop-blur-md border-b border-slate-800">
        <div className="flex items-center gap-4">
          <Link 
            href={`/${semester}/${subject}`}
            className="p-2 hover:bg-slate-800 rounded-full transition-colors text-slate-400 hover:text-white"
          >
            <ArrowLeft className="w-5 h-5" />
          </Link>
          <div className="flex items-center gap-3">
            <span className="text-sm font-bold text-white flex items-center gap-2">
              <FileCode className="w-4 h-4 text-green-500" />
              {fileName}
            </span>
            <div className="h-4 w-px bg-slate-700"></div>
            <span className="text-xs text-slate-500 font-mono">
              {code.split('\n').length} lines
            </span>
            <span className="text-slate-700">•</span>
            <span className="text-xs text-green-600 font-mono uppercase tracking-wider">
              {language}
            </span>
          </div>
        </div>
        <div className="flex items-center gap-3">
          <CopyButton code={code} />
          <a
            href={`/api/download?file=${semester}/${subjectName}/${fileName}`}
            className="flex items-center gap-2 px-3 py-1.5 text-xs font-bold bg-green-600 text-black rounded hover:bg-green-500 transition-all shadow-[0_0_15px_rgba(34,197,94,0.3)] hover:shadow-[0_0_25px_rgba(34,197,94,0.6)]"
          >
            <Download className="w-3 h-3" />
            DOWNLOAD
          </a>
        </div>
      </div>


      <div className="max-w-6xl mx-auto p-4 md:p-8">
        <div className="rounded-xl overflow-hidden border border-slate-800 shadow-2xl bg-[#1e1e1e]">
          <SyntaxHighlighter
            language={language}
            style={vscDarkPlus}
            customStyle={{ 
              margin: 0, 
              padding: '1.5rem', 
              fontSize: '14px', 
              lineHeight: '1.6',
              background: '#0d0d0d' 
            }}
            showLineNumbers={true}
            wrapLines={true}
          >
            {code}
          </SyntaxHighlighter>
        </div>
      </div>
    </div>
  );
}