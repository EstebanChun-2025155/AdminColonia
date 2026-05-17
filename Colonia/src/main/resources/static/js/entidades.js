 const overlay = document.getElementById('modalEliminar');

    function abrirModal(btn) {
        const url    = btn.getAttribute('data-url');
        const nombre = btn.getAttribute('data-nombre') || '—';

        document.getElementById('modalFormEliminar').action = url;
        document.getElementById('modalNombreTexto').textContent = nombre;

        overlay.classList.add('activo');
        document.body.style.overflow = 'hidden';
    }

    function cerrarModal() {
        overlay.classList.remove('activo');
        document.body.style.overflow = '';
    }

    overlay.addEventListener('click', cerrarModal);

    document.addEventListener('keydown', function (e) {
        if (e.key === 'Escape') cerrarModal();
    });