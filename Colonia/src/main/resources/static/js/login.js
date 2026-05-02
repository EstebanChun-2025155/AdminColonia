    (function () {
        const card = document.getElementById('loginCard');
        const svg  = document.getElementById('neonSvg');
        const neon = document.getElementById('neonRect');
        const aura = document.getElementById('auraRect');
        const base = document.getElementById('baseRect');
        const grad = document.getElementById('movingGrad');

        const RX = 22, PAD = 1.5;
        let W, H, perim;

        function setup() {
            W = card.offsetWidth;
            H = card.offsetHeight;
            svg.setAttribute('width',   W);
            svg.setAttribute('height',  H);
            svg.setAttribute('viewBox', `0 0 ${W} ${H}`);
            const rw = W - PAD * 2;
            const rh = H - PAD * 2;
            [neon, aura, base].forEach(el => {
                el.setAttribute('width',  rw);
                el.setAttribute('height', rh);
            });
            perim = Math.round(2 * (rw + rh) - 8 * RX + 2 * Math.PI * RX);
            const dash = perim * 0.18;
            neon.setAttribute('stroke-dasharray', `${dash} ${perim - dash}`);
        }

        let prog = 0, last = null;
        const SPEED = 6.0;

        function frame(ts) {
            if (!last) last = ts;
            const dt = Math.min(ts - last, 50);
            last = ts;
            prog = (prog + SPEED * (dt / 16.67)) % perim;
            neon.setAttribute('stroke-dashoffset', perim - prog);

            const t  = prog / perim;
            const cx = PAD + (W - PAD * 2) * Math.sin(t * Math.PI * 2) * 0.5 + W / 2;
            const cy = PAD + (H - PAD * 2) * (-Math.cos(t * Math.PI * 2)) * 0.5 + H / 2;
            grad.setAttribute('x1', cx - 80);
            grad.setAttribute('y1', cy - 80);
            grad.setAttribute('x2', cx + 80);
            grad.setAttribute('y2', cy + 80);

            requestAnimationFrame(frame);
        }

        setTimeout(() => { setup(); requestAnimationFrame(frame); }, 120);
    })();