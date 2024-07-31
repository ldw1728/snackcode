const toolbarOptions = [
    [{ 'font': [] }],
    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
    [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['link', 'blockquote'],
    [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
    [{ 'align': [] }, { 'indent': '-1'}, { 'indent': '+1' }, { 'direction': 'rtl' }],          // outdent/indent
    [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
    ['clean']                                         // remove formatting button
];

function createQuillEditor(selector) {

    const editor = new Quill(selector, {
        modules: {
            toolbar: toolbarOptions
        },
        theme: 'snow'
    });

    return editor;
}