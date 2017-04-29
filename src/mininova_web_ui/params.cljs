(ns mininova-web-ui.params)

(def waveforms (range 0 72))  ;; TODO: Proper enum of waveforms
(def filter-types (range 0 14)) ;; TODO

(def osc-waveform-enum
  [;; 0-10
   "Sine" "Triangle" "Sawtooth" "Saw9:1PW" "Saw8:2PW" "Saw7:3PW" "Saw6:4PW" "Saw5:5PW" "Saw4:6PW" "Saw3:7PW" "Saw2:8PW"
   ;; 11-20
   "Saw1:9PW" "PW" "Square" "BassCamp" "Bass_FM" "EP_Dull" "EP_Bell" "Clav" "DoubReed" "Retro"
   ;; 21-30
   "StrnMch1" "StrnMch2" "Organ_1" "Organ_2" "EvilOrg" "HiStuff" "Bell_FM1" "Bell_FM2" "DigBell1" "DigBell2"
   ;; 31-40
   "DigBell3" "DigBell4" "DigiPad" "Wtable1" "Wtable2" "Wtable3" "Wtable4" "Wtable5" "Wtable6" "Wtable7"
   ;; 41-50
   "Wtable8" "Wtable9" "Wtable10" "Wtable11" "Wtable12" "Wtable13" "Wtable14" "Wtable15" "Wtable16" "Wtable17"
   ;; 51-60
   "Wtable18" "Wtable19" "Wtable20" "Wtable21" "Wtable22" "Wtable23" "Wtable24" "Wtable25" "Wtable26" "Wtable27"
   ;; 61-70
   "Wtable28" "Wtable29" "Wtable30" "Wtable31" "Wtable32" "Wtable33" "Wtable34" "Wtable35" "Wtable36" "AudInL/M"
   ;; 71
   "AudioInR"])

(def filter-drive-type-enum
  ["Diode" "Valve" "Clipper" "XOver" "Rectify" "BitsDown" "RateDown"])
(def filter-type-enum
  ["Lp6NoRes" "LP12" "LP18" "LP24" "BP6/\\6" "BP12/\\12" "BP6/\\12" "BP12/\\6" "BP6/\\18" "BP18/\\6" "HP6NoRes" "HP12" "HP18" "HP24"])

(def env-anim-trigger-enum
  ["Off"
   "A1ReTrig" "A2ReTrig" "A3ReTrig" "A4ReTrig" "A5ReTrig" "A6ReTrig" "A7ReTrig" "A8ReTrig"
   "A1Triggr" "A2Triggr" "A3Triggr" "A4Triggr" "A5Triggr" "A6Triggr" "A7Triggr" "A8Triggr"
   "A1Enable" "A2Enable" "A3Enable" "A4Enable" "A5Enable" "A6Enable" "A7Enable" "A8Enable"])

(def lfo-waveform-enum
  [;; 0-9
   "Sine" "Triangle" "Sawtooth" "Square" "Rand S/H" "Time S/H" "PianoEnv" "Seq 1" "Seq 2" "Seq 3"
   ;; 10-19
   "Seq 4" "Seq 5" "Seq 6" "Seq 7" "Altern 1" "Altern 2" "Altern 3" "Altern 4" "Altern 5" "Altern 6"
   ;; 20-29
   "Altern 7" "Altern 8" "Chromat" "Chrom 16" "Major" "Major 7" "Minor7" "MinArp1" "MinArp2" "Diminish"
   ;; 30-37
   "DecMinor" "Minor3rd" "Pedal" "4ths" "4ths x12" "1625 Maj" "1625 Min" "2511"])

(def sync-enum
  [;; 0-9
   "Off" "32nd T" "32nd" "16th T" "16th" "8th T" "16th D" "8th" "4th T" "8th D"
   ;; 10-19
   "4th" "1 + 1/3" "4th D" "2nd" "2 + 2/3" "3 beats" "4 beats" "5 + 1/3" "6 beats" "8 beats"
   ;; 20-29
   "10 + 2/3" "12 beats" "13 + 1/3" "16 beats" "18 beats" "18 + 2/3" "20 beats" "21 + 1/3" "24 beats" "28 beats"
   ;; 30-35
   "30 beats" "32 beats" "36 beats" "42 beats" "48 beats" "64 beats"])

(def arp-pattern-enum
  ["32nd T" "32nd" "16th T" "16th" "8th T" "16th D" "8th" "4th T" "8th D" "4th"
   "1 + 1/3" "4th D" "2nd" "2 + 2/3" "3 beats" "4 beats" "5 + 1/3" "6 beats" "8 beats"])

(def fx-select-enum
  ["Bypass" "EQ" "Compres1" "Compres2" "Distort1" "Distort2" "Delay 1" "Delay 2" "Reverb 1" "Reverb 2" "Chorus 1" "Chorus 2" "Chorus 3" "Chorus 4" "Gator"])

(def mod-matrix-source-enum
  ["Direct" "ModWheel" "AftTouch" "Express" "Velocity" "Keyboard" "LFO1 +" "LFO1 +/-" "LFO2 +" "LFO2 +/-" "LFO3 +" "LFO3 +/-" "Env Amp" "Env Filt" "Env 3" "Env 4" "Env 5" "Env 6" "AudInEnv"])

(def anim-trigger-enum
  ["Off" "A1ReTrig" "A2ReTrig" "A3ReTrig" "A4ReTrig" "A5ReTrig" "A6ReTrig" "A7ReTrig" "A8ReTrig"])

(def params
  {:voice/polyphony-mode {:cc 3 :in [0 4] :enum ["Mono" "Mono AG" "Poly 1" "Poly 2" "Mono 2"] :offset 34}
   :voice/portamento-rate {:cc 5 :in [0 127]}
   :voice/pre-glide {:cc 9 :in [52 76] :out [-12 12]}
   :voice/portamento-mode {:cc 12 :in [0 1] :enum ["Expo" "Linear"]}
   ;; FIXME: Handle this range properly
   :voice/keyboard-octave {:cc 13 :in [124 4] :out [-4 4] :offset 0}
   :voice/unison {:cc 14 :in [0 4] :enum ["Off" 1 2 3 4]}
   :voice/unison-detune {:cc 15 :in [0 127]}

   :midi/modulation {:cc 1 :in [0 127] :offset 0}
   :midi/breath-controller {:cc 2 :in [0 127] :offset 0}
   :midi/data-entry-msb {:cc 6 :in [0 127] :offset 0}
   :midi/expression-controller {:cc 11 :in [0 127] :offset 0}
   :midi/bank-lsb {:cc 32 :in [0 127] :enum ["A" "B" "C"] :offset 0}
   :midi/data-entry-lsb {:cc 38 :in [0 127] :offset 0}
   :midi/data-increment {:cc 96 :in [0 127] :offset 0}
   :midi/data-decrement {:cc 97 :in [0 127] :offset 0}
   :midi/nprn-lsb {:cc 98 :in [0 127] :offset 0}
   :midi/nprn-msb {:cc 99 :in [0 127] :offset 0}
   ;; FIXME: Does this need an lsb and msb combo? DOES!
   :midi/tempo {:cc [2 63] :in [40 140] :offset 0}
   :midi/all-sounds-off {:cc 120 :in [0] :offset 0}
   :midi/local-off-on {:cc 122 :in [33 99] :offset 0}

   :patch/category {:cc [2 64] :in [0 14] :enum ["None" "Arp" "Bass" "Bell" "Classic" "Drum" "Keyboard" "Lead" "Movement" "Pad" "Poly" "SFX" "String" "ExtInput" "Voc/Tune"]}
   :patch/genre {:cc [2 65] :in [0 9] :enum ["None" "Classic" "D&B/Brks" "House" "Industrl" "Jazz" "R&B/HHop" "Rock/Pop" "Techno" "Dubstep"] :offset 32}
   :patch/patch-select {:cc [63 0] :in [0 2] :enum ["Decrement Patch" "Get Program Change" "Increment Patch"] :offset 0}
   :patch/bank-select {:cc [63 1] :in [1 3] :enum ["A" "B" "C"] :offset 0}

   ;; TODO: Do we really need :out here?
   :osc/drift {:cc 16 :in [0 127] :offset 43}
   ;; TODO: 0-119: 0"-357", 120: "Free", ie. a map function
   :osc/phase {:cc 17 :in [0 120] :offset 44}
   :osc/fixed-transpose {:cc 18 :in [0 127] :offset 45}
   :osc/vibrato-speed {:cc 76 :in [0 127] :offset 41}
   :osc/vibrato-depth {:cc 77 :in [0 127] :offset 42}

   :osc-1/wave {:cc 19 :in [0 71] :enum osc-waveform-enum :offset 46}
   :osc-1/wave-interpolate {:cc 20 :in [0 127] :offset 47}
   :osc-1/pulse-width-index {:cc 21 :in [0 127] :out [-64 63] :offset 48}
   :osc-1/virtual-sync-depth {:cc 22 :in [0 127] :offset 49}
   :osc-1/hardness {:cc 23 :in [0 127] :offset 50}
   :osc-1/density {:cc 24 :in [0 127] :offset 51}
   :osc-1/density-detune {:cc 25 :in [0 127] :offset 52}
   :osc-1/semitones {:cc 26 :in [0 127] :out [-64 63] :offset 53}
   :osc-1/cents {:cc 27 :in [0 127] :out [-64 63] :offset 54}
   :osc-1/pitch-bend {:cc 28 :in [52 76] :out [-12 12] :offset 55}

   :osc-2/wave {:cc 29 :in [0 71] :enum osc-waveform-enum :offset 56}
   :osc-2/wave-interpolate {:cc 30 :in [0 127] :offset 57}
   :osc-2/pulse-width-index {:cc 31 :in [0 127] :out [-64 63] :offset 58}
   :osc-2/virtual-sync-depth {:cc 33 :in [0 127] :offset 59}
   :osc-2/hardness {:cc 34 :in [0 127] :offset 60}
   :osc-2/density {:cc 35 :in [0 127] :offset 61}
   :osc-2/density-detune {:cc 36 :in [0 127] :offset 62}
   :osc-2/semitones {:cc 37 :in [0 127] :out [-64 63] :offset 63}
   :osc-2/cents {:cc 39 :in [0 127] :out [-64 63] :offset 64}
   :osc-2/pitch-bend {:cc 0x28 :in [52 76] :out [-12 12] :offset 65}

   :osc-3/wave {:cc 41 :in [0 71] :enum osc-waveform-enum :offset 66}
   :osc-3/wave-interpolate {:cc 42 :in [0 127] :offset 67}
   :osc-3/pulse-width-index {:cc 43 :in [0 127] :out [-64 63] :offset 68}
   :osc-3/virtual-sync-depth {:cc 44 :in [0 127] :offset 69}
   :osc-3/hardness {:cc 45 :in [0 127] :offset 70}
   :osc-3/density {:cc 46 :in [0 127] :offset 71}
   :osc-3/density-detune {:cc 47 :in [0 127] :offset 72}
   :osc-3/semitones {:cc 48 :in [0 127] :out [-64 63] :offset 73}
   :osc-3/cents {:cc 49 :in [0 127] :out [-64 63] :offset 74}
   :osc-3/pitch-bend {:cc 50 :in [52 76] :out [-12 12] :offset 75}

   :mixer/wet-level {:cc 8 :in [0 127]}
   :mixer/osc-1-level {:cc 51 :in [0 127]}
   :mixer/osc-2-level {:cc 52 :in [0 127]}
   :mixer/osc-3-level {:cc 53 :in [0 127]}
   :mixer/ring-mod-13-level {:cc 54 :in [0 127]}
   :mixer/ring-mod-23-level {:cc 55 :in [0 127]}
   :mixer/noise-level {:cc 56 :in [0 127]}
   :mixer/noise-colour {:cc 57 :in [0 3] :enum ["White" "High" "Band" "HiBand"]}
   :mixer/pre-fx-level {:cc 58 :in [52 82] :out [-12 18]}
   :mixer/post-fx-level {:cc 59 :in [52 82] :out [-12 18]}

   :filter/routing {:cc 60 :in [0 5] :enum ["Bypass" "Single" "Series" "Parallel" "Parallal" "Drum"] :offset 86}
   :filter/balance {:cc 61 :in [0 127] :out [-64 63] :offset 87}
   ;; FIXME: Figure out the patch encoding
   :filter/freq-link {:cc [0 122] :in [42 43] :enum ["Off" "On"] :offset 88}
   :filter/res-link {:cc [0 122] :in [44 45] :enum ["Off" "On"] :offset 88}

   :filter-1/drive {:cc 63 :in [0 127] :out [0 127] :offset 89}
   :filter-1/drive-type {:cc 65 :in [0 6] :enum filter-drive-type-enum :offset 90}
   :filter-1/type {:cc 68 :in [0 13] :enum filter-type-enum :offset 91}
   :filter-1/track {:cc 69 :in [0 127] :offset 93}
   :filter-1/resonance {:cc 71 :in [0 127] :offset 94}
   :filter-1/frequency {:cc 74 :in [0 127] :offset 92}
   :filter-1/q-normalise {:cc 78 :in [0 127] :offset 95}
   :filter-1/env-2->freq {:cc 79 :in [0 127] :out [-64 63] :offset 96}

   :filter-2/drive {:cc 80 :in [0 127] :out [0 127] :offset 97}
   :filter-2/drive-type {:cc 81 :in [0 6] :enum filter-drive-type-enum :offset 98}
   :filter-2/type {:cc 82 :in [0 13] :enum filter-type-enum :offset 99}
   :filter-2/frequency {:cc 83 :in [0 127] :offset 100}
   :filter-2/track {:cc 84 :in [0 127] :offset 101}
   :filter-2/resonance {:cc 85 :in [0 127] :offset 102}
   :filter-2/q-normalise {:cc 86 :in [0 127] :offset 103}
   :filter-2/env-2->freq {:cc 87 :in [0 127] :out [-64 63] :offset 104}

   :animate/hold-button-on {:cc [60 16] :in [0 1] :enum ["Animate Hold Off" "Animate Hold On"] :offset 0}
   ;; NOTE: Are these actually 0-127 0,127 or 0-1?
   :animate-1/hold {:cc [60 0] :in [0 127] :offset 0}
   :animate-2/hold {:cc [60 1] :in [0 127] :offset 0}
   :animate-3/hold {:cc [60 2] :in [0 127] :offset 0}
   :animate-4/hold {:cc [60 3] :in [0 127] :offset 0}
   :animate-5/hold {:cc [60 4] :in [0 127] :offset 0}
   :animate-6/hold {:cc [60 5] :in [0 127] :offset 0}
   :animate-7/hold {:cc [60 6] :in [0 127] :offset 0}
   :animate-8/hold {:cc [60 7] :in [0 127] :offset 0}

   :env/track-centre {:cc 106 :in [0 127]}

   :env-1/sustain {:cc 70 :in [0 127] :offset 118}
   :env-1/release {:cc 72 :in [0 127] :offset 119}
   :env-1/attack {:cc 73 :in [0 127] :offset 116}
   :env-1/decay {:cc 75 :in [0 127] :offset 117}
   :env-1/velocity {:cc 108 :in [0 127] :out [-64 63] :offset 115}
   :env-1/sustain-rate {:cc 109 :in [0 127] :out [-64 63] :offset 120}
   :env-1/sustain-time {:cc 110 :in [0 127] :offset 121}
   :env-1/ad-repeats {:cc 111 :in [0 127] :offset 122}
   :env-1/attack-track {:cc 112 :in [0 127] :out [-64 63] :offset 123}
   :env-1/decay-track {:cc 113 :in [0 127] :out [-64 63] :offset 124}
   :env-1/level-track {:cc 114 :in [0 127] :out [-64 63] :offset 125}
   :env-1/attack-slope {:cc 115 :in [0 127] :offset 126}
   :env-1/decay-slope {:cc 116 :in [0 127] :offset 127}
   :env-1/anim-trigger {:cc 117 :in [0 8] :enum env-anim-trigger-enum :offset 128}
   ;; TODO: Figure out the encoding
   :env-1/trigger {:cc [0 122] :in [0 1] :enum ["Single" "Multi"] :offset 112}

   :env-2/velocity {:cc [0 0] :in [0 127] :out [-64 63] :offset 129}
   :env-2/attack {:cc [0 1] :in [0 127] :offset 130}
   :env-2/decay {:cc [0 2] :in [0 127] :offset 131}
   :env-2/sustain {:cc [0 3] :in [0 127] :offset 132}
   :env-2/release {:cc [0 4] :in [0 127] :offset 133}
   :env-2/sustain-rate {:cc [0 5] :in [0 127] :out [-64 63] :offset 134}
   :env-2/sustain-time {:cc [0 6] :in [0 127] :offset 135}
   :env-2/ad-repeats {:cc [0 7] :in [0 127] :offset 136}
   :env-2/attack-track {:cc [0 8] :in [0 127] :out [-64 63] :offset 137}
   :env-2/decay-track {:cc [0 9] :in [0 127] :out [-64 63] :offset 138}
   :env-2/level-track {:cc [0 10] :in [0 127] :out [-64 63] :offset 139}
   :env-2/attack-slope {:cc [0 11] :in [0 127] :offset 140}
   :env-2/decay-slope {:cc [0 12] :in [0 127] :offset 141}
   :env-2/anim-trigger {:cc [0 13] :in [0 24] :enum env-anim-trigger-enum :offset 142}
   :env-2/trigger {:cc [0 122] :in [2 3] :enum ["Single" "Multi"] :offset 112}

   :env-3/delay {:cc [0 14] :in [0 127] :offset 143}
   :env-3/attack {:cc [0 15] :in [0 127] :offset 144}
   :env-3/decay {:cc [0 16] :in [0 127] :offset 145}
   :env-3/sustain {:cc [0 17] :in [0 127] :offset 146}
   :env-3/release {:cc [0 18] :in [0 127] :offset 147}
   :env-3/sustain-rate {:cc [0 19] :in [0 127] :out [-64 63] :offset 148}
   :env-3/sustain-time {:cc [0 20] :in [0 127] :offset 149}
   :env-3/ad-repeats {:cc [0 21] :in [0 127] :offset 150}
   :env-3/attack-track {:cc [0 22] :in [0 127] :out [-64 63] :offset 151}
   :env-3/decay-track {:cc [0 23] :in [0 127] :out [-64 63] :offset 152}
   :env-3/level-track {:cc [0 24] :in [0 127] :out [-64 63] :offset 153}
   :env-3/attack-slope {:cc [0 25] :in [0 127] :offset 154}
   :env-3/decay-slope {:cc [0 26] :in [0 127] :offset 155}
   :env-3/anim-trigger {:cc [0 27] :in [0 24] :enum env-anim-trigger-enum :offset 156}
   :env-3/trigger {:cc [0 122] :in [4 5] :enum ["Single" "Multi"]  :offset 112}

   :env-4/delay {:cc [0 28] :in [0 127] :offset 157}
   :env-4/attack {:cc [0 29] :in [0 127] :offset 158}
   :env-4/decay {:cc [0 30] :in [0 127] :offset 159}
   :env-4/sustain {:cc [0 31] :in [0 127] :offset 160}
   :env-4/release {:cc [0 32] :in [0 127] :offset 161}
   :env-4/sustain-rate {:cc [0 33] :in [0 127] :out [-64 63] :offset 162}
   :env-4/sustain-time {:cc [0 34] :in [0 127] :offset 163}
   :env-4/ad-repeats {:cc [0 35] :in [0 127] :offset 164}
   :env-4/attack-track {:cc [0 36] :in [0 127] :out [-64 63] :offset 165}
   :env-4/decay-track {:cc [0 37] :in [0 127] :out [-64 63] :offset 166}
   :env-4/level-track {:cc [0 38] :in [0 127] :out [-64 63] :offset 167}
   :env-4/attack-slope {:cc [0 39] :in [0 127] :offset 168}
   :env-4/decay-slope {:cc [0 40] :in [0 127] :offset 169}
   :env-4/anim-trigger {:cc [0 41] :in [0 24] :enum env-anim-trigger-enum :offset 170}
   :env-4/trigger {:cc [0 122] :in [6 7] :enum ["Single" "Multi"] :offset 112}

   :env-5/delay {:cc [0 42] :in [0 127] :offset 171}
   :env-5/attack {:cc [0 43] :in [0 127] :offset 172}
   :env-5/decay {:cc [0 44] :in [0 127] :offset 173}
   :env-5/sustain {:cc [0 45] :in [0 127] :offset 174}
   :env-5/release {:cc [0 46] :in [0 127] :offset 175}
   :env-5/sustain-rate {:cc [0 47] :in [0 127] :out [-64 63] :offset 176}
   :env-5/sustain-time {:cc [0 48] :in [0 127] :offset 177}
   :env-5/ad-repeats {:cc [0 49] :in [0 127] :offset 178}
   :env-5/attack-track {:cc [0 50] :in [0 127] :out [-64 63] :offset 179}
   :env-5/decay-track {:cc [0 51] :in [0 127] :out [-64 63] :offset 180}
   :env-5/level-track {:cc [0 52] :in [0 127] :out [-64 63] :offset 181}
   :env-5/attack-slope {:cc [0 53] :in [0 127] :offset 182}
   :env-5/decay-slope {:cc [0 54] :in [0 127] :offset 183}
   :env-5/anim-trigger {:cc [0 55] :in [0 24] :enum env-anim-trigger-enum :offset 184}
   :env-5/trigger {:cc [0 122] :in [8 9] :enum ["Single" "Multi"] :offset 112}

   :env-6/delay {:cc [0 56] :in [0 127] :offset 185}
   :env-6/attack {:cc [0 57] :in [0 127] :offset 186}
   :env-6/decay {:cc [0 58] :in [0 127] :offset 187}
   :env-6/sustain {:cc [0 59] :in [0 127] :offset 188}
   :env-6/release {:cc [0 60] :in [0 127] :offset 189}
   :env-6/sustain-rate {:cc [0 61] :in [0 127] :out [-64 63] :offset 190}
   :env-6/sustain-time {:cc [0 62] :in [0 127] :offset 191}
   :env-6/ad-repeats {:cc [0 63] :in [0 127] :offset 192}
   :env-6/attack-track {:cc [0 64] :in [0 127] :out [-64 63] :offset 193}
   :env-6/decay-track {:cc [0 65] :in [0 127] :out [-64 63] :offset 194}
   :env-6/level-track {:cc [0 66] :in [0 127] :out [-64 63] :offset 195}
   :env-6/attack-slope {:cc [0 67] :in [0 127] :offset 196}
   :env-6/decay-slope {:cc [0 68] :in [0 127] :offset 197}
   :env-6/anim-trigger {:cc [0 69] :in [0 24] :enum env-anim-trigger-enum :offset 198}
   :env-6/trigger {:cc [0 122] :in [10 11] :enum ["Single" "Multi"] :offset 112}

   :lfo-1/waveform {:cc [0 70] :in [0 37] :enum lfo-waveform-enum :offset 199}
   :lfo-1/phase-offset {:cc [0 71] :in [0 119] :out [0 357] :offset 200}
   :lfo-1/slew-rate {:cc [0 72] :in [0 127] :offset 201}
   :lfo-1/delay {:cc [0 74] :in [0 127] :offset 203}
   :lfo-1/delay-sync {:cc [0 75] :in [0 35] :enum sync-enum :offset 204}
   :lfo-1/rate {:cc [0 76] :in [0 127] :offset 205}
   :lfo-1/rate-sync {:cc [0 77] :in [0 35] :enum sync-enum :offset 206}
   ;; TODO: Figure out the encoding
   :lfo-1/one-shot {:cc [0 122] :in [12 13] :enum ["Normal" "OneShot"] :offset 207}
   :lfo-1/key-sync {:cc [0 122] :in [14 15] :enum ["FreeRun" "KeySync"] :offset 207}
   :lfo-1/common-sync {:cc [0 122] :in [16 17] :enum ["Normal" "Common"] :offset 207}
   :lfo-1/delay-trigger {:cc [0 122] :in [18 19] :enum ["Single" "Multi"] :offset 207}
   ;; TODO: Figure out the encoding
   :lfo-1/fade-mode {:cc [1 123] :in [0 3] :enum ["Fade In" "Fade Out" "Gate In" "Gate Out"] :offset 379}

   :lfo-2/waveform {:cc [0 79] :in [0 37] :enum lfo-waveform-enum :offset 208}
   :lfo-2/phase-offset {:cc [0 80] :in [0 119] :out [0 357] :offset 209}
   :lfo-2/slew-rate {:cc [0 81] :in [0 127] :offset 210}
   :lfo-2/delay {:cc [0 83] :in [0 127] :offset 212}
   :lfo-2/delay-sync {:cc [0 84] :in [0 35] :enum sync-enum :offset 213}
   :lfo-2/rate {:cc [0 85] :in [0 127] :offset 214}
   :lfo-2/rate-sync {:cc [0 86] :in [0 35] :enum sync-enum :offset 215}
   :lfo-2/one-shot {:cc [0 122] :in [22 23] :enum ["Normal" "OneShot"] :offset 216}
   :lfo-2/key-sync {:cc [0 122] :in [24 25] :enum ["FreeRun" "KeySync"] :offset 216}
   :lfo-2/common-sync {:cc [0 122] :in [26 27] :enum ["Normal" "Common"] :offset 216}
   :lfo-2/delay-trigger {:cc [0 122] :in [28 29] :enum ["Single" "Multi"] :offset 216}
   :lfo-2/fade-mode {:cc [1 123] :in [4 7] :enum ["Fade In" "Fade Out" "Gate In" "Gate Out"] :offset 379}

   :lfo-3/waveform {:cc [0 88] :in [0 37] :enum lfo-waveform-enum :offset 217}
   :lfo-3/phase-offset {:cc [0 89] :in [0 119] :out [0 357] :offset 218}
   :lfo-3/slew-rate {:cc [0 90] :in [0 127] :offset 219}
   :lfo-3/delay {:cc [0 92] :in [0 127] :offset 221}
   :lfo-3/delay-sync {:cc [0 93] :in [0 35] :enum sync-enum :offset 222}
   :lfo-3/rate {:cc [0 94] :in [0 127] :offset 223}
   :lfo-3/rate-sync {:cc [0 95] :in [0 35] :enum sync-enum :offset 224}
   :lfo-3/one-shot {:cc [0 122] :in [32 33] :enum ["Normal" "OneShot"] :offset 225}
   :lfo-3/key-sync {:cc [0 122] :in [34 35] :enum ["FreeRun" "KeySync"] :offset 225}
   :lfo-3/common-sync {:cc [0 122] :in [36 37] :enum ["Normal" "Common"] :offset 225}
   :lfo-3/delay-trigger {:cc [0 122] :in [38 39] :enum ["Single" "Multi"] :offset 225}
   ;; NOTE: Docs say :in [4 7] but thats lfo-2/fade-mode
   :lfo-3/fade-mode {:cc [1 123] :in [8 11] :enum ["Fade In" "Fade Out" "Gate In" "Gate Out"]}

   :fx-pan/position {:cc 10 :in [0 127] :out [-64 63]}
   :fx-pan/rate {:cc 88 :in [0 127]}
   :fx-pan/sync {:cc 89 :in [0 35] :enum sync-enum}
   :fx-pan/mod-depth {:cc 90 :in [0 127]}

   :fx/routing {:cc [0 97] :in [0 7]} ;; TODO: Enum
   :fx/feedback {:cc [0 98] :in [0 127]}

   :fx-1/level {:cc 91 :in [0 127]}
   :fx-1/select {:cc [0 99] :in [0 14] :enum fx-select-enum}

   :fx-2/level {:cc 92 :in [0 127]}
   :fx-2/select {:cc [0 100] :in [0 14] :enum fx-select-enum}

   :fx-3/level {:cc 93 :in [0 127]}
   :fx-3/select {:cc [0 101] :in [0 14] :enum fx-select-enum}

   :fx-4/level {:cc 94 :in [0 127]}
   :fx-5/level {:cc 95 :in [0 127]}

   :fx-4/select {:cc [0 102] :in [0 14] :enum fx-select-enum}
   :fx-5/select {:cc [0 103] :in [0 14] :enum fx-select-enum}

   :equaliser/bass-frequency {:cc [0 104] :in [0 127]}
   :equaliser/bass-level {:cc [0 105] :in [0 127] :out [-64 63]}
   :equaliser/mid-frequency {:cc [0 106] :in [0 127]}
   :equaliser/mid-level {:cc [0 107] :in [0 127] :out [-64 63]}
   :equaliser/treble-frequency {:cc [0 108] :in [0 127]}
   :equaliser/treble-level {:cc [0 109] :in [0 127] :out [-64 63]}

   :compressor-1/ratio {:cc [0 110] :in [0 127]}
   :compressor-1/treshold {:cc [0 111] :in [0 60] :out [-60 0]}
   :compressor-1/attack {:cc [0 112] :in [0 127]}
   :compressor-1/release {:cc [0 113] :in [0 127]}
   :compressor-1/hold {:cc [0 114] :in [0 127]}
   :compressor-1/gain {:cc [0 115] :in [0 127]}

   :compressor-2/ratio {:cc [0 116] :in [0 127]}
   :compressor-2/treshold {:cc [0 117] :in [0 60] :out [-60 0]}
   :compressor-2/attack {:cc [0 118] :in [0 127]}
   :compressor-2/release {:cc [0 119] :in [0 127]}
   :compressor-2/hold {:cc [0 120] :in [0 127]}
   :compressor-2/gain {:cc [0 121] :in [0 127]}

   :distortion-1/type {:cc [1 0] :in [0 6] :enum ["Diode" "Valve" "Clipper" "XOver" "Rectify" "BitsDown" "RateDown"] :offset 256}
   :distortion-1/compensation {:cc [1 1] :in [0 127] :offset 257}
   :distortion-1/level {:cc [1 2] :in [52 82] :out [-12 18]}

   :distortion-2/type {:cc [1 3] :in [0 6] :enum ["Diode" "Valve" "Clipper" "XOver" "Rectify" "BitsDown" "RateDown"]}
   :distortion-2/compensation {:cc [1 4] :in [0 127]}
   :distortion-2/level {:cc [1 5] :in [52 82] :out [-12 18]}

   :delay-1/time {:cc [1 6] :in [0 127]}
   :delay-1/time-sync {:cc [1 7] :in [0 35]} ;; TODO: Enum
   :delay-1/feedback {:cc [1 8] :in [0 127]}
   :delay-1/width {:cc [1 9] :in [0 127]}
   :delay-1/lr-ratio {:cc [1 10] :in [0 12]} ;; TODO: Enum
   :delay-1/slew-rate {:cc [1 11] :in [0 127]}

   :delay-2/time {:cc [1 12] :in [0 127]}
   :delay-2/time-sync {:cc [1 13] :in [0 35]} ;; TODO: Enum
   :delay-2/feedback {:cc [1 14] :in [0 127]}
   :delay-2/width {:cc [1 15] :in [0 127]}
   :delay-2/lr-ratio {:cc [1 16] :in [0 12]} ;; TODO: Enum
   :delay-2/slew-rate {:cc [1 17] :in [0 127]}

   :reverb-1/type {:cc [1 18] :in [0 5] :enum ["Chamber" "SmlRoom" "Lrgroom" "SmlHall" "LrgHall" "GrtHall"]}
   :reverb-1/decay {:cc [1 19] :in [0 127]}
   :reverb-1/damping {:cc [1 20] :in [0 127]}

   :reverb-2/type {:cc [1 21] :in [0 5] :enum ["Chamber" "SmlRoom" "Lrgroom" "SmlHall" "LrgHall" "GrtHall"]}
   :reverb-2/decay {:cc [1 22] :in [0 127]}
   :reverb-2/damping {:cc [1 23] :in [0 127]}

   :chorus-1/type {:cc [1 24] :in [0 1] :enum ["Phaser" "Chorus"]}
   :chorus-1/rate {:cc [1 25] :in [0 127]}
   :chorus-1/rate-sync {:cc [1 26] :in [0 35]} ;; TODO: Enum
   :chorus-1/feedback {:cc [1 27] :in [0 127] :out [-64 63]}
   :chorus-1/mod-depth {:cc [1 28] :in [0 127]}
   :chorus-1/delay {:cc [1 29] :in [0 127]}

   :chorus-2/type {:cc [1 30] :in [0 1] :enum ["Phaser" "Chorus"]}
   :chorus-2/rate {:cc [1 31] :in [0 127]}
   :chorus-2/rate-sync {:cc [1 32] :in [0 35] :offset 288} ;; TODO: Enum
   :chorus-2/feedback {:cc [1 33] :in [0 127] :out [-64 63] :offset 289}
   :chorus-2/mod-depth {:cc [1 34] :in [0 127]}
   :chorus-2/delay {:cc [1 35] :in [0 127]}

   :chorus-3/type {:cc [1 36] :in [0 1] :enum ["Phaser" "Chorus"]}
   :chorus-3/rate {:cc [1 37] :in [0 127]}
   :chorus-3/rate-sync {:cc [1 38] :in [0 35]} ;; TODO: Enum
   :chorus-3/feedback {:cc [1 39] :in [0 127] :out [-64 63]}
   :chorus-3/mod-depth {:cc [1 40] :in [0 127]}
   :chorus-3/delay {:cc [1 41] :in [0 127]}

   :chorus-4/type {:cc [1 42] :in [0 1] :enum ["Phaser" "Chorus"]}
   :chorus-4/rate {:cc [1 43] :in [0 127]}
   :chorus-4/rate-sync {:cc [1 44] :in [0 35]} ;; TODO: Enum
   :chorus-4/feedback {:cc [1 45] :in [0 127] :out [-64 63]}
   :chorus-4/mod-depth {:cc [1 46] :in [0 127]}
   :chorus-4/delay {:cc [1 47] :in [0 127]}

   :gator/on {:cc [0 122] :in [52 53] :enum ["Off" "On"]}
   :gator/key-sync {:cc [0 122] :in [54 55] :enum ["FreeRun" "KeySync"]}
   :gator/key-latch {:cc [0 122] :in [56 57] :enum ["LatchOff" "LatchOn"]}
   :gator/rate-sync {:cc [1 49] :in [0 35]} ;; TODO: Enum
   :gator/mode {:cc [1 50] :in [0 5] :enum ["Mono16" "MonoAlt1" "MonoAlt2" "Stereo16" "SterAlt" "SterAlt2"]}
   :gator/edge-slew {:cc [1 52] :in [0 127]}
   :gator/hold {:cc [1 53] :in [0 127]}
   :gator/lr-delay {:cc [1 54] :in [0 127] :out [-64 63]}
   :gator/level-1 {:cc [5 0] :in [0 7]}
   :gator/level-2 {:cc [5 1] :in [0 7]}
   :gator/level-3 {:cc [5 2] :in [0 7]}
   :gator/level-4 {:cc [5 3] :in [0 7]}
   :gator/level-5 {:cc [5 4] :in [0 7]}
   :gator/level-6 {:cc [5 5] :in [0 7]}
   :gator/level-7 {:cc [5 6] :in [0 7]}
   :gator/level-8 {:cc [5 7] :in [0 7]}
   :gator/level-9 {:cc [5 8] :in [0 7]}
   :gator/level-10 {:cc [5 9] :in [0 7]}
   :gator/level-11 {:cc [5 10] :in [0 7]}
   :gator/level-12 {:cc [5 11] :in [0 7]}
   :gator/level-13 {:cc [5 12] :in [0 7]}
   :gator/level-14 {:cc [5 13] :in [0 7]}
   :gator/level-15 {:cc [5 14] :in [0 7]}
   :gator/level-16 {:cc [5 15] :in [0 7]}
   :gator/level-17 {:cc [5 16] :in [0 7]}
   :gator/level-18 {:cc [5 17] :in [0 7]}
   :gator/level-19 {:cc [5 18] :in [0 7]}
   :gator/level-20 {:cc [5 19] :in [0 7]}
   :gator/level-21 {:cc [5 20] :in [0 7]}
   :gator/level-22 {:cc [5 21] :in [0 7]}
   :gator/level-23 {:cc [5 22] :in [0 7]}
   :gator/level-24 {:cc [5 23] :in [0 7]}
   :gator/level-25 {:cc [5 24] :in [0 7]}
   :gator/level-26 {:cc [5 25] :in [0 7]}
   :gator/level-27 {:cc [5 26] :in [0 7]}
   :gator/level-28 {:cc [5 27] :in [0 7]}
   :gator/level-29 {:cc [5 28] :in [0 7]}
   :gator/level-30 {:cc [5 29] :in [0 7] :offset 480}
   :gator/level-31 {:cc [5 30] :in [0 7]}
   :gator/level-32 {:cc [5 31] :in [0 7] :offset 481}

   :vocoder/on {:cc [0 122] :in [58 59] :enum ["Off" "On"]}
   :vocoder/sibilance-type {:cc [0 122] :in [60 61] :enum ["HiPass" "Noise"]}
   :vocoder/freeze {:cc [0 122] :in [62 63] :enum ["UnFreeze" "Freeze"]}
   :vocoder/all-max {:cc [0 122] :in [64 65] :enum ["AllMax Off" "AllMax On"]}
   :vocoder/vocoder-input {:cc [0 122] :in [66 67] :enum ["Audio In" "VTOutput"]}
   :vocoder/width {:cc [1 57] :in [0 127]}
   :vocoder/sibilance {:cc [1 58] :in [0 127]}
   :vocoder/spec-shift {:cc [1 59] :in [0 127] :out [-64 63]}
   :vocoder/spec-spread {:cc [1 60] :in [0 127] :out [-64 63]}
   :vocoder/level {:cc [1 71] :in [0 127]}
   :vocoder/carrier-level {:cc [1 72] :in [0 127]}
   :vocoder/modulator-level {:cc [1 73] :in [0 127]}
   :vocoder/resonance {:cc [1 74] :in [0 127]}
   :vocoder/decay {:cc [1 75] :in [0 127]}
   :vocoder/gate-treshold {:cc [1 76] :in [0 96] :out [-96 0]}
   :vocoder/gate-release {:cc [1 77] :in [0 127]}
   :vocoder/spectrum-level-1 {:cc [6 0] :in [0 127] :offset 0}
   :vocoder/spectrum-level-2 {:cc [6 1] :in [0 127] :offset 0}
   :vocoder/spectrum-level-3 {:cc [6 2] :in [0 127] :offset 0}
   :vocoder/spectrum-level-4 {:cc [6 3] :in [0 127] :offset 0}
   :vocoder/spectrum-level-5 {:cc [6 4] :in [0 127] :offset 0}
   :vocoder/spectrum-level-6 {:cc [6 5] :in [0 127] :offset 0}
   :vocoder/spectrum-level-7 {:cc [6 6] :in [0 127] :offset 0}
   :vocoder/spectrum-level-8 {:cc [6 7] :in [0 127] :offset 0}
   :vocoder/spectrum-level-9 {:cc [6 8] :in [0 127] :offset 0}
   :vocoder/spectrum-level-10 {:cc [6 9] :in [0 127] :offset 0}
   :vocoder/spectrum-level-11 {:cc [6 10] :in [0 127] :offset 0}
   :vocoder/spectrum-level-12 {:cc [6 11] :in [0 127] :offset 0}
   :vocoder/spectrum-level-13 {:cc [6 12] :in [0 127] :offset 0}
   :vocoder/spectrum-level-14 {:cc [6 13] :in [0 127] :offset 0}
   :vocoder/spectrum-level-15 {:cc [6 14] :in [0 127] :offset 0}
   :vocoder/spectrum-level-16 {:cc [6 15] :in [0 127] :offset 0}
   :vocoder/spectrum-level-17 {:cc [6 16] :in [0 127] :offset 0}
   :vocoder/spectrum-level-18 {:cc [6 17] :in [0 127] :offset 0}
   :vocoder/spectrum-level-19 {:cc [6 18] :in [0 127] :offset 0}
   :vocoder/spectrum-level-20 {:cc [6 19] :in [0 127] :offset 0}
   :vocoder/spectrum-level-21 {:cc [6 20] :in [0 127] :offset 0}
   :vocoder/spectrum-level-22 {:cc [6 21] :in [0 127] :offset 0}
   :vocoder/spectrum-level-23 {:cc [6 22] :in [0 127] :offset 0}
   :vocoder/spectrum-level-24 {:cc [6 23] :in [0 127] :offset 0}
   :vocoder/spectrum-level-25 {:cc [6 24] :in [0 127] :offset 0}
   :vocoder/spectrum-level-26 {:cc [6 25] :in [0 127] :offset 0}
   :vocoder/spectrum-level-27 {:cc [6 26] :in [0 127] :offset 0}
   :vocoder/spectrum-level-28 {:cc [6 27] :in [0 127] :offset 0}
   :vocoder/spectrum-level-29 {:cc [6 28] :in [0 127] :offset 0}
   :vocoder/spectrum-level-30 {:cc [6 29] :in [0 127] :offset 0}
   :vocoder/spectrum-level-31 {:cc [6 30] :in [0 127] :offset 0}
   :vocoder/spectrum-level-32 {:cc [6 31] :in [0 127] :offset 0}
   :vocoder/spectrum-resample {:cc [6 32] :in [1 1] :offset 0}

   :chorder/transpose {:cc [1 78] :in [53 75] :out [-11 11]}
   :chorder/on {:cc [1 79] :in [0 1] :enum ["Off" "On"]}
   ;; FIXME: Hangs
   :chorder/chorder-count {:cc [7 16] :in [0 10] :offset 0}
   ;; FIXME: is it 0-127, 0,127 or 0-1?
   :chorder/chorder-key-2 {:cc [7 17] :in [0 127] :offset 0}
   :chorder/chorder-key-3 {:cc [7 18] :in [0 127] :offset 0}
   :chorder/chorder-key-4 {:cc [7 19] :in [0 127] :offset 0}
   :chorder/chorder-key-5 {:cc [7 20] :in [0 127] :offset 0}
   :chorder/chorder-key-6 {:cc [7 21] :in [0 127] :offset 0}
   :chorder/chorder-key-7 {:cc [7 22] :in [0 127] :offset 0}
   :chorder/chorder-key-8 {:cc [7 23] :in [0 127] :offset 0}
   :chorder/chorder-key-9 {:cc [7 24] :in [0 127] :offset 0}
   :chorder/chorder-key-10 {:cc [7 25] :in [0 127] :offset 0}

   :vocal-tune/shift {:cc [1 80] :in [40 88] :out [-24 24]}
   :vocal-tune/bend {:cc [1 81] :in [40 88] :out [-24 24]}
   ;; NOTE: Collides with mod-matrix-9/source-1 (0-18) ?! doesn't collide with (0-11) in lfo-n/fade-mode
   :vocal-tune/mode {:cc [1 123] :in [16 19] :enum ["Off" "Scale Correction" "KBCtrl" "Pitch"]}
   ;; NOTE: Doesn't collide with (0-11) in lfo-n/fade-mode or (16-19)
   :vocal-tune/insert {:cc [1 123] :in [25 27] :enum ["PreFilter" "PostFilter" "PreFx"]}
   :vocal-tune/scale-type {:cc [2 56] :in [0 5] :enum ["Played" "Chromatic" "Major" "NatMinor" "HarMinor" "MelMinor"]}
   :vocal-tune/scale-key {:cc [2 57] :in [0 11] :enum ["C" "C#" "D" "D#" "E" "F" "F#" "G" "G#" "A" "A#" "B"]}
   :vocal-tune/correction-time {:cc [2 58] :in [0 127]}
   :vocal-tune/level {:cc [2 59] :in [0 127]}
   :vocal-tune/vibrato {:cc [2 60] :in [0 127]}
   :vocal-tune/vibrato-mod-wheel {:cc [2 61] :in [0 127]}
   :vocal-tune/vibrato-rate {:cc [2 62] :in [0 127]}

   ;; TODO: Figure out the encoding @ 317
   ;; FIXME: Modifying either `on` or `key-latch` changes both in the store because they share
   ;; the `cc`. Make `::ui/control` action smarter about this
   :arp/on {:cc [0 122] :in [46 47] :enum ["Off" "On"] :offset 317}
   :arp/key-latch {:cc [0 122] :in [50 51] :enum ["Latch Off" "Latch On"] :offset 317}
   :arp/octaves {:cc [1 62] :in [0 3] :out [1 4] :offset 318}
   :arp/rate-sync {:cc [1 63] :in [0 18] :enum arp-pattern-enum :offset 319}
   ;; FIXME: This was detected at 320 AND 325?
   :arp/gate {:cc [1 64] :in [1 127] :offset 320}
   :arp/mode {:cc [1 65] :in [0 6] :enum ["Up" "Down" "UpDown" "UpDown2" "Played" "Random" "Chord"] :offset 321}
   :arp/pattern {:cc [1 66] :in [0 18] :enum arp-pattern-enum :offset 322}
   :arp/swing {:cc [1 68] :in [1 99] :offset 324}
   ;; Managed parameter for UltraNova compatibility_
   ;; :arp/mini-nova {:cc [127 127] :in [0 1] :offset 0}
   :arp/length {:cc [60 40] :in [2 8] :offset 325}

   ;; FIXME: step 1 was detected at 325?!
   ;; TODO: Figure out the encoding @ 326
   :arp-1/step {:cc [60 32], :in [0 1], :offset 326},
   :arp-2/step {:cc [60 33], :in [0 1], :offset 326},
   :arp-3/step {:cc [60 34], :in [0 1], :offset 326},
   :arp-4/step {:cc [60 35], :in [0 1], :offset 326},
   :arp-5/step {:cc [60 36], :in [0 1], :offset 326},
   :arp-6/step {:cc [60 37], :in [0 1], :offset 326},
   :arp-7/step {:cc [60 38], :in [0 1], :offset 326},
   :arp-8/step {:cc [60 39], :in [0 1], :offset 326},

   :mod-matrix-1/source-1 {:cc [1 83] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-1/source-2 {:cc [1 84] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-1/anim-trigger {:cc [1 85] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-1/depth {:cc [1 86] :in [0 127] :out [-64 63]}
   :mod-matrix-1/destination {:cc [1 87] :in [0 69]} ;; TODO: Enum

   :mod-matrix-2/source-1 {:cc [1 88] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-2/source-2 {:cc [1 89] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-2/anim-trigger {:cc [1 90] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-2/depth {:cc [1 91] :in [0 127] :out [-64 63]}
   :mod-matrix-2/destination {:cc [1 92] :in [0 69]} ;; TODO: Enum

   :mod-matrix-3/source-1 {:cc [1 93] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-3/source-2 {:cc [1 94] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-3/anim-trigger {:cc [1 95] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-3/depth {:cc [1 96] :in [0 127] :out [-64 63] :offset 352}
   :mod-matrix-3/destination {:cc [1 97] :in [0 69] :offset 353} ;; TODO: Enum

   :mod-matrix-4/source-1 {:cc [1 98] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-4/source-2 {:cc [1 99] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-4/anim-trigger {:cc [1 100] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-4/depth {:cc [1 101] :in [0 127] :out [-64 63]}
   :mod-matrix-4/destination {:cc [1 102] :in [0 69]} ;; TODO: Enum

   :mod-matrix-5/source-1 {:cc [1 103] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-5/source-2 {:cc [1 104] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-5/anim-trigger {:cc [1 105] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-5/depth {:cc [1 106] :in [0 127] :out [-64 63]}
   :mod-matrix-5/destination {:cc [1 107] :in [0 69]} ;; TODO: Enum

   :mod-matrix-6/source-1 {:cc [1 108] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-6/source-2 {:cc [1 109] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-6/anim-trigger {:cc [1 110] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-6/depth {:cc [1 111] :in [0 127] :out [-64 63]}
   :mod-matrix-6/destination {:cc [1 112] :in [0 69]} ;; TODO: Enum

   :mod-matrix-7/source-1 {:cc [1 113] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-7/source-2 {:cc [1 114] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-7/anim-trigger {:cc [1 115] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-7/depth {:cc [1 116] :in [0 127] :out [-64 63]}
   :mod-matrix-7/destination {:cc [1 117] :in [0 69]} ;; TODO: Enum

   :mod-matrix-8/source-1 {:cc [1 118] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-8/source-2 {:cc [1 119] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-8/anim-trigger {:cc [1 120] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-8/depth {:cc [1 121] :in [0 127] :out [-64 63]}
   :mod-matrix-8/destination {:cc [1 122] :in [0 69]} ;; TODO: Enum

   ;; FIXME: Does this really collide with lfo-n/fade-mode?!
   :mod-matrix-9/source-1 {:cc [1 123] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-9/source-2 {:cc [1 124] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-9/anim-trigger {:cc [1 125] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-9/depth {:cc [1 126] :in [0 127] :out [-64 63]}
   :mod-matrix-9/destination {:cc [1 127] :in [0 69]} ;; TODO: Enum

   :mod-matrix-10/source-1 {:cc [2 0] :in [0 18] :enum mod-matrix-source-enum :offset 384}
   :mod-matrix-10/source-2 {:cc [2 1] :in [0 18] :enum mod-matrix-source-enum :offset 385}
   :mod-matrix-10/anim-trigger {:cc [2 2] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-10/depth {:cc [2 3] :in [0 127] :out [-64 63]}
   :mod-matrix-10/destination {:cc [2 4] :in [0 69]} ;; TODO: Enum

   :mod-matrix-11/source-1 {:cc [2 5] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-11/source-2 {:cc [2 6] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-11/anim-trigger {:cc [2 7] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-11/depth {:cc [2 8] :in [0 127] :out [-64 63]}
   :mod-matrix-11/destination {:cc [2 9] :in [0 69]} ;; TODO: Enum

   :mod-matrix-12/source-1 {:cc [2 10] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-12/source-2 {:cc [2 11] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-12/anim-trigger {:cc [2 12] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-12/depth {:cc [2 13] :in [0 127] :out [-64 63]}
   :mod-matrix-12/destination {:cc [2 14] :in [0 69]} ;; TODO: Enum

   :mod-matrix-13/source-1 {:cc [2 15] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-13/source-2 {:cc [2 16] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-13/anim-trigger {:cc [2 17] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-13/depth {:cc [2 18] :in [0 127] :out [-64 63]}
   :mod-matrix-13/destination {:cc [2 19] :in [0 69]} ;; TODO: Enum

   :mod-matrix-14/source-1 {:cc [2 20] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-14/source-2 {:cc [2 21] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-14/anim-trigger {:cc [2 22] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-14/depth {:cc [2 23] :in [0 127] :out [-64 63]}
   :mod-matrix-14/destination {:cc [2 24] :in [0 69]} ;; TODO: Enum

   :mod-matrix-15/source-1 {:cc [2 25] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-15/source-2 {:cc [2 26] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-15/anim-trigger {:cc [2 27] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-15/depth {:cc [2 28] :in [0 127] :out [-64 63]}
   :mod-matrix-15/destination {:cc [2 29] :in [0 69]} ;; TODO: Enum

   :mod-matrix-16/source-1 {:cc [2 30] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-16/source-2 {:cc [2 31] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-16/anim-trigger {:cc [2 32] :in [0 8] :enum anim-trigger-enum :offset 416}
   :mod-matrix-16/depth {:cc [2 33] :in [0 127] :out [-64 63] :offset 417}
   :mod-matrix-16/destination {:cc [2 34] :in [0 69]} ;; TODO: Enum

   :mod-matrix-17/source-1 {:cc [2 35] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-17/source-2 {:cc [2 36] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-17/anim-trigger {:cc [2 37] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-17/depth {:cc [2 38] :in [0 127] :out [-64 63]}
   :mod-matrix-17/destination {:cc [2 39] :in [0 69]} ;; TODO: Enum

   :mod-matrix-18/source-1 {:cc [2 40] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-18/source-2 {:cc [2 41] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-18/anim-trigger {:cc [2 42] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-18/depth {:cc [2 43] :in [0 127] :out [-64 63]}
   :mod-matrix-18/destination {:cc [2 44] :in [0 69]} ;; TODO: Enum

   :mod-matrix-19/source-1 {:cc [2 45] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-19/source-2 {:cc [2 46] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-19/anim-trigger {:cc [2 47] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-19/depth {:cc [2 48] :in [0 127] :out [-64 63]}
   :mod-matrix-19/destination {:cc [2 49] :in [0 69]} ;; TODO: Enum

   :mod-matrix-20/source-1 {:cc [2 50] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-20/source-2 {:cc [2 51] :in [0 18] :enum mod-matrix-source-enum}
   :mod-matrix-20/anim-trigger {:cc [2 52] :in [0 8] :enum anim-trigger-enum}
   :mod-matrix-20/depth {:cc [2 53] :in [0 127] :out [-64 63]}
   :mod-matrix-20/destination {:cc [2 54] :in [0 69]} ;; TODO: Enum

   :tweaks/assignment-1 {:cc [4 0] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-2 {:cc [4 1] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-3 {:cc [4 2] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-4 {:cc [4 3] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-5 {:cc [4 4] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-6 {:cc [4 5] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-7 {:cc [4 6] :in [0 125] :offset 0} ;; TODO: Enum
   :tweaks/assignment-8 {:cc [4 7] :in [0 125] :offset 0} ;; TODO: Enum

   :global/global-protect {:cc [64 0] :in [0 1] :enum ["Protect Off" "Protect On"] :offset 0}
   :global/midi-channel {:cc [64 4] :in [0 15] :offset 0}
   :global/tuning-cents {:cc [64 6] :in [15 114] :out [-50 50] :offset 0}
   :global/transpose {:cc [64 7] :in [40 88] :out [-24 24] :offset 0}
   :global/velocity-curve {:cc [64 9] :in [0 127] :offset 0} ;; TODO: Enum
   :global/clock-source {:cc [64 11] :in [0 3] :enum ["Internal" "USB" "DIN" "Auto"] :offset 0}
   :global/sustain-pedal {:cc [64 14] :in [0 2] :enum ["Auto" "N.Open" "N.Closed"] :offset 0}
   :global/wheel-lights {:cc [64 20] :in [0 1] :enum ["Off" "On"] :offset 0}
   :global/pot-pickup {:cc [64 21] :in [0 1] :enum ["Off" "On"] :offset 0}
   :global/standby-mode {:cc [64 22] :in [0 2] :enum ["Off" "On" "10 mins"] :offset 0}
   :global/arp-midi {:cc [64 23] :in [0 1] :enum ["MIDI->Arp" "Arp->MIDI"] :offset 0}
   :global/input-gain {:cc [64 28] :in [21 97] :offset 0}
   :global/input-fx {:cc [64 30] :in [0 127] :offset 0}})

(def cc->param
  (->> params
      (map #(assoc (second %) :id (first %)))
      (group-by :cc)))

(println ::cc->param cc->param)
