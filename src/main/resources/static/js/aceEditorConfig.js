const aceEditorEl = document.querySelector('#aceEditor');
let aceEditor;

function createAceEditor() {
    aceEditor = ace.edit('aceEditor');
    aceEditor.setTheme('ace/theme/cloud9_day');
    //aceEditor.session.setMode("ace/mode/javascript");
    aceEditor.session.on('change', function(delta) {
        document.dForm.codeCntns.value = aceEditor.getValue();
    });
}

function setAceEditorValue(text) {
    aceEditor.setValue(text);
}

function setAceEditorMode(mode){
    aceEditor.session.setMode(`ace/mode/${mode}`);
}

function removeAceEditor(){
    aceEditorEl.classList.add('hidden');
}

function showAceEditor() {
    aceEditorEl.classList.remove('hidden');
}


