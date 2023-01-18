import { DEFAULT_PIXEL_SIZE } from './config';

import { rand, parse } from './utils';

const colors = [
  '#f0c188',
  '#ef7e45',
  '#d44f26',
  '#ab3232',
  '#66333c',
  '#392245',
  '#5c4b4b',
  '#917d65',
  '#b5a774',
  '#81906c',
  '#5c635e',
  '#363c3d',
  '#091d21',
  '#405351',
  '#78756c',
  '#a8a192',
  '#cfcbb8',
  '#ebe8e2',
  '#a6dde3',
  '#72b4c4',
  '#5f96b0',
  '#4d73a1',
  '#204d85',
  '#161d80',
  '#4f3c80',
  '#78618c',
  '#b089a1',
  '#d4b8b2',
];
var endex = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz=_';

function decode(charcode) {
  return charcode.length < 2
    ? endex.indexOf(charcode)
    : decode(charcode.slice(0, -1)) * endex.length +
        endex.indexOf(charcode.slice(-1));
}

export function generateSprite(s, scale = DEFAULT_PIXEL_SIZE) {
  let sprite = parse(s, (e) => (e === '-' ? -1 : decode(e)));
  const height = sprite.length - 1;
  const bs = sprite.reduce(
    (r, line, y) =>
      r +
      line.reduce(
        (l, colorIndex, x) =>
          (x === 0 && y === height) || colorIndex === -1
            ? l
            : `${l}${x * scale}px ${-(height - y) * scale}px 0 0 ${
                colors[colorIndex]
              }, `,
        ''
      ),
    ''
  );
  return {
    bs: bs.substring(0, bs.length - 2),
    bg: sprite[height][0] === -1 ? '' : colors[sprite[height][0]],
    s: `${scale}px`,
  };
}

export function renderSprite(object, sprite) {
  const { bs, bg, s } = generateSprite(sprite, object.scale);
  object.el.style.height = s;
  object.el.style.width = s;
  object.el.style.boxShadow = bs;
  object.el.style.backgroundColor = bg;
}
