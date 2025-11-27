import { source } from "framer-motion/client";
import fs from "fs";
import path from "path";

const contentDirectory = path.join(process.cwd(),"content");
const isDirectory = (source: string) => fs.lstatSync(source).isDirectory;

export function getSems(){
    if(!fs.existsSync(contentDirectory)) return [];
    return fs.readdirSync(contentDirectory).filter(name => isDirectory(path.join(contentDirectory,name))).sort();
}

export function getSubs(semester: string){
    const semPath = path.join(contentDirectory, semester);
    if(!fs.existsSync(semPath)) return [];
    return fs.readdirSync(semPath).filter(name => isDirectory(path.join(semPath,name)));
}

export function getExps(semester: string,subject: string){
    const subPath = path.join(contentDirectory, semester,subject);
    if(!fs.existsSync(subPath)) return [];
    return fs.readdirSync(subPath).filter(name => !name.startsWith(".")).map(file => ({name: file,path: `${semester}/${subject}/${file}`}));
}

export function getCodes(semester: string,subject: string,filename: string){
    const filePath = path.join(contentDirectory, semester, subject, filename);
    return fs.readFileSync(filePath,"utf8");
}