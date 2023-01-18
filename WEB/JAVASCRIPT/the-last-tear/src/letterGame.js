import { unBindCommands, bindCommands } from './input';
import { pTimeout, defer } from './utils';
import Game from './game';

const letters = $('#letters');
const game = $('#game');
const go = $('#play');

const phrase =
  'My dear Suzy I know we had our differences but I love you please forgive me';
let word;
let p;

let endPromise;
let i = 0;
let timeout;
let isPlaying = false;
let isGood = false;

function keyListener(event) {
  const key = event.key;
  if (isPlaying === false) {
    if (key === 'Enter') {
      return isGood ? endgame() : countdown();
    }
  } else {
    if (word && key === word[i]) {
      if (timeout) {
        clearTimeout(timeout);
      }
      const el = $(`.key-${i}`);
      el?.classList.remove('active');
      i++;
      playWord();
    }
  }
}

function createdkey(key, i) {
  const el = $$('div');
  el.classList.add('key', `key-${i}`);
  el.innerText = key;
  letters.appendChild(el);
}

function playWord() {
  if (word.length === i) {
    return p.resolve();
  }
  const el = $(`.key-${i}`);
  el?.classList.add('active');
  timeout = setTimeout(() => {
    p.reject();
  }, 1000);
}

async function playSequence(words) {
  isGood = true;
  while (isGood && words.length) {
    letters.innerHTML = '';
    word = words.shift();
    word.split('').forEach(createdkey);
    i = 0;
    p = defer();
    playWord();
    try {
      await p.promise;
    } catch (e) {
      isGood = false;
      isPlaying = false;
    }
  }
  if (!isGood) {
    letters.innerText = 'You failed !';
    go.innerText = 'Press enter to try again';
    go.style.display = 'block';
  } else {
    isPlaying = false;
    letters.innerText = 'Well done !';
    go.innerText = 'Press enter go back';
    go.style.display = 'block';
  }
}

function endgame() {
  bindCommands();
  const gI = Game.getIns();
  game.style.display = 'none';
  gI.lev.classList.remove('bg');
  document.removeEventListener('keydown', keyListener);
  endPromise.resolve();
  isPlaying = false;
}

async function countdown() {
  isPlaying = true;
  go.style.display = 'none';
  letters.innerHTML = '3';
  await pTimeout(1000);
  letters.innerHTML = '2';
  await pTimeout(1000);
  letters.innerHTML = '1';
  await pTimeout(1000);
  playSequence(phrase.toLowerCase().split(' '));
}

export function playLetterGame(gI, p) {
  endPromise = p;
  unBindCommands();
  document.addEventListener('keydown', keyListener);
  game.style.display = 'flex';
  gI.lev.classList.add('bg');
}
