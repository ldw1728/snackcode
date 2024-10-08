const aceEditorEl = document.querySelector('#aceEditor');
let aceEditor;

const codeModes = [
    'abap',
    'abc',
    'actionscript',
    'ada',
    'alda',
    'apache_conf',
    'apex',
    'aql',
    'asciidoc',
    'asl',
    'assembly_arm32',
    'assembly_x86',
    'astro',
    'autohotkey',
    'batchfile',
    'bibtex',
    'c_cpp',
    'c9search',
    'cirru',
    'clojure',
    'cobol',
    'coffee',
    'coldfusion',
    'crystal',
    'csharp',
    'csound_document',
    'csound_orchestra',
    'csound_score',
    'css',
    'curly',
    'cuttlefish',
    'd',
    'dart',
    'diff',
    'django',
    'dockerfile',
    'dot',
    'drools',
    'edifact',
    'eiffel',
    'ejs',
    'elixir',
    'elm',
    'erlang',
    'flix',
    'forth',
    'fortran',
    'fsharp',
    'fsl',
    'ftl',
    'gcode',
    'gherkin',
    'gitignore',
    'glsl',
    'gobstones',
    'golang',
    'graphqlschema',
    'groovy',
    'haml',
    'handlebars',
    'haskell',
    'haskell_cabal',
    'haxe',
    'hjson',
    'html',
    'html_elixir',
    'html_ruby',
    'ini',
    'io',
    'ion',
    'jack',
    'jade',
    'java',
    'javascript',
    'jexl',
    'json',
    'json5',
    'jsoniq',
    'jsp',
    'jssm',
    'jsx',
    'julia',
    'kotlin',
    'latex',
    'latte',
    'less',
    'liquid',
    'lisp',
    'livescript',
    'log',
    'logiql',
    'logtalk',
    'lsl',
    'lua',
    'luapage',
    'lucene',
    'makefile',
    'markdown',
    'mask',
    'matlab',
    'maze',
    'mediawiki',
    'mel',
    'mips',
    'mixal',
    'mushcode',
    'mysql',
    'nasal',
    'nginx',
    'nim',
    'nix',
    'nsis',
    'nunjucks',
    'objectivec',
    'ocaml',
    'odin',
    'partiql',
    'pascal',
    'perl',
    'pgsql',
    'php',
    'php_laravel_blade',
    'pig',
    'plsql',
    'powershell',
    'praat',
    'prisma',
    'prolog',
    'properties',
    'protobuf',
    'prql',
    'puppet',
    'python',
    'qml',
    'r',
    'raku',
    'razor',
    'rdoc',
    'red',
    'rhtml',
    'robot',
    'rst',
    'ruby',
    'rust',
    'sac',
    'sass',
    'scad',
    'scala',
    'scheme',
    'scrypt',
    'scss',
    'sh',
    'sjs',
    'slim',
    'smarty',
    'smithy',
    'snippets',
    'soy_template',
    'space',
    'sparql',
    'sql',
    'sqlserver',
    'stylus',
    'svg',
    'swift',
    'tcl',
    'terraform',
    'tex',
    'text',
    'textile',
    'toml',
    'tsx',
    'turtle',
    'twig',
    'typescript',
    'vala',
    'vbscript',
    'velocity',
    'verilog',
    'vhdl',
    'visualforce',
    'vue',
    'wollok',
    'xml',
    'xquery',
    'yaml',
    'zeek',
    'zig',
];

function createAceEditor(selector = 'aceEditor') {

    const aceEditor = ace.edit(selector);
    aceEditor.setTheme('ace/theme/cloud9_day');
    aceEditor.setOptions({
        selectionStyle: 'line',
        copyWithEmptySelection: true,
        navigateWithinSoftTabs: true
    });
    //aceEditor.session.setMode("ace/mode/javascript");
    aceEditor.session.on('change', function(delta) {
        if (document?.dForm?.codeCntns) {
            document.dForm.codeCntns.value = aceEditor.getValue();
        }
    });

    return aceEditor;
}

// function setAceEditorReadOnly() {
//     aceEditor.setReadOnly(true)
// }
//
// function setAceEditorValue(text) {
//     aceEditor.setValue(text);
// }
//
// function setAceEditorMode(mode){
//     aceEditor.session.setMode(`ace/mode/${mode}`);
// }

function removeAceEditor(selector = '#aceEditor'){
    document.querySelector(selector).classList.add('hidden');
}

function showAceEditor(selector = '#aceEditor') {
    document.querySelector(selector).classList.remove('hidden');
}


