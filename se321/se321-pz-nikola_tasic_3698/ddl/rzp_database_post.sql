INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (2, 1, 1, 1, 0, 'Markdown Here
', 'markdown-here', 'Markdown Here is a Google Chrome, Firefox, Safari, Opera, and Thunderbird extension that lets you write email? in Markdown? and render them before sending. It also supports syntax highlighting (just specify the language in a fenced code block).

Writing email with code in it is pretty tedious. Writing Markdown with code in it is easy. I found myself writing email in Markdown in the Github in-browser editor, then copying the preview into email. This is a pretty absurd workflow, so I decided create a tool to write and render Markdown right in the email.', '# ![](https://raw.github.com/adam-p/markdown-here/master/src/common/images/icon48.png) Markdown Here

[**Visit the website.**](http://markdown-here.com)<br>
[**Get it for Chrome.**](https://chrome.google.com/webstore/detail/elifhakcjgalahccnjkneoccemfahfoa)<br>
[**Get it for Firefox.**](https://addons.mozilla.org/en-US/firefox/addon/markdown-here/)<br>
[**Get it for Safari.**](https://s3.amazonaws.com/markdown-here/markdown-here.safariextz)<br>
[**Get it for Thunderbird and Postbox.**](https://addons.mozilla.org/en-US/thunderbird/addon/markdown-here/)<br>
[**Get it for Opera.**](https://addons.opera.com/en/extensions/details/markdown-here/)<br>
[**Discuss it and ask questions in the Google Group.**](https://groups.google.com/forum/?fromgroups#!forum/markdown-here/)<br>

*Markdown Here* is a Google Chrome, Firefox, Safari, Opera, and Thunderbird extension that lets you write email<sup>?</sup> in Markdown<sup>?</sup> and __ender__ them before sending. It also supports syntax highlighting (just specify the language in a fenced code block).

Writing email with code in it is pretty tedious. Writing Markdown with code in it is easy. I found myself writing email in Markdown in the Github in-browser editor, then copying the preview into email. This is a pretty absurd workflow, so I decided create a tool to write and render Markdown right in the email.

To discover what can be done with Markdown in *Markdown Here*, check out the [Markdown Here Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Here-Cheatsheet) and the other [wiki pages](https://github.com/adam-p/markdown-here/wiki).

<sup>?: And Google Groups posts, and Blogger posts, and Evernote notes, and Wordpress posts! [See more](#compatibility).</sup><br>
<sup>?: And TeX mathematical formulae!</sup>

![](https://raw.github.com/adam-p/markdown-here/master/store-assets/markdown-here-image1.gimp.png)

### Table of Contents
**[Installation Instructions](#installation-instructions)**<br>
**[Usage Instructions](#usage-instructions)**<br>
**[Troubleshooting](#troubleshooting)**<br>
**[Compatibility](#compatibility)**<br>
**[Notes and Miscellaneous](#notes-and-miscellaneous)**<br>
**[Building the Extension Bundles](#building-the-extension-bundles)**<br>
**[Next Steps, Credits, Feedback, License](#next-steps)**<br>

## Installation Instructions

### Chrome

#### Chrome Web Store

Go to the [Chrome Web Store page for *Markdown Here*](https://chrome.google.com/webstore/detail/elifhakcjgalahccnjkneoccemfahfoa) and install normally.

After installing, make sure to reload your webmail or restart Chrome!

#### Manual/Development

1. Clone this repo.
2. In Chrome, open the Extensions settings. (Wrench button, Tools, Extensions.)
3. On the Extensions settings page, click the "Developer Mode" checkbox.
4. Click the now-visible "Load unpacked extension?" button. Navigate to the directory where you cloned the repo, then the `src` directory under that.
5. The *Markdown Here* extension should now be visible in your extensions list.
6. Reload your webmail page (and maybe application) before trying to convert an email.

### Firefox and Thunderbird

#### Mozilla Add-ons site

Go to the [Firefox Add-ons page for *Markdown Here*](https://addons.mozilla.org/en-US/firefox/addon/markdown-here/) and install normally.

Or go to the "Tools > Add-ons" menu and then search for "Markdown Here".

After installing, make sure to restart Firefox/Thunderbird!

**Note:** It takes up to a month for Mozilla to approve changes to the Firefox/Thunderbird extension, so updates (features, fixes) will lag behind what is shown here. You can manually choose to install the newest version before it''s reviewed from the list of versions: [https://addons.mozilla.org/en-US/firefox/addon/markdown-here/versions/](https://addons.mozilla.org/en-US/firefox/addon/markdown-here/versions/)

#### Manual/Development

1. Clone this repo.
2. Follow the instructions in the MDN ["Setting up an extension development environment"](https://developer.mozilla.org/en/Setting_up_extension_development_environment) article.

### Safari

[Download the extension directly.](https://s3.amazonaws.com/markdown-here/markdown-here.safariextz) When it has finished downloading, double click it to install.

#### Preferences

To get to the Markdown Here preferences, open the Safari preferences and then go to the "Extensions" tab. Then click the "Click me to show Markdown Here options" box.

### Opera

Note that *Markdown Here* only works with Opera versions 16 and higher (i.e., the ones that are based on Chromium).

Go to the [Opera Add-ons store page for *Markdown Here*](https://addons.opera.com/en/extensions/details/markdown-here/) and install normally.

After installing, make sure to reload your webmail or restart Chrome!

## Usage Instructions

Install it, and then?

1. In Chrome/Safari/Opera, *make sure* you reload your web mail page before trying to use Markdown Here.
2. In Chrome/Firefox/Safari/Opera, log into your Gmail, Hotmail, or Yahoo account and start a new email. In Thunderbird, start a new message.
3. Make sure you''re using the rich editor.
   * In Gmail, click the "Rich formatting" link, if it''s visible.
   * In Thunderbird, make sure "Compose messages in HTML format" is enabled in your "Account Settings", "Composition & Addressing" pane.
4. Compose an email in Markdown. For example:

   <pre>
   **Hello** `world`.

   ```javascript
   alert(''Hello syntax highlighting.'');
   ```
   </pre>

5. Right-click in the compose box and choose the "Markdown Toggle" item from the context menu. Or click the button that appears in your address bar. Or use the hotkey (<kbd>CTRL</kbd>+<kbd>ALT</kbd>+<kbd>M</kbd> by default).
6. You should see your email rendered correctly from Markdown into rich HTML.
7. Send your awesome email to everyone you know. It will appear to them the same way it looks to you.

### Revert to Markdown

After rendering your Markdown to pretty HTML, you can still get back to your original Markdown. Just right-click anywhere in the newly rendered Markdown and click "Markdown Toggle" -- your email compose body will change back to the Markdown you had written.

Note that any changes you make to the pretty HTML will be lost when you revert to Markdown.

In Gmail, you can also use the browser''s Undo command (<kbd>CTRL</kbd>+<kbd>Z</kbd> / <kbd>CMD</kbd>+<kbd>Z</kbd>, or from the Edit menu). Be warned that you might also lose the last few characters you entered.

### Replies

In Gmail, Thunderbird, and Google Groups, you can use "Markdown Toggle" normally: just write your reply (top, bottom, inline, wherever) and then convert. The original email that you''re replying to will be left alone. (Technically: Existing `blockquote` blocks will be left intact.)

In Hotmail and Yahoo (which don''t put the original in a `blockquote`), and optionally in Gmail, Thunderbird, and Google Groups, you can ensure that only the part of the reply that you wrote gets converted by selecting what you want to convert and then clicking "Markdown Toggle" -- see the next section.

### Selection/Piecemeal Conversion

Sometimes you don''t want to convert the entire email; sometimes your email isn''t entirely Markdown. To convert only part of the email, select the text (with your mouse or keyboard), right-click on it, and click the "Markdown Toggle" menu item. Your selection is magically rendered into pretty HTML.

To revert back to Markdown, just put your cursor anywhere in the block of converted text, right click, and click the "Markdown Toggle" menu item again. Now it''s magically back to the original Markdown.

![screenshot of selection conversion](https://raw.github.com/adam-p/markdown-here/master/store-assets/markdown-here-image2.gimp.png)

#### Things to know about converting/reverting a selection

* If you select only part of a block of text, only that text will be converted. The converted block will be wrapped in a paragraph element, so the original line will be broken up. You probably don''t want to ever do this.

* You can select and revert multiple converted blocks at the same time. One upshot of this is that you can select your entire email, click "Markdown Toggle", and all portions of it that you had converted will be reverted.

* If you don''t have anything selected when you click "Markdown Toggle", *Markdown Here* will check if there are converted blocks anywhere in the message and revert them. If there no converted blocks are found, it will convert the entire email.

### Options

The *Markdown Here* Options page can be accessed via the Chrome, Firefox, Safari, or Thunderbird extensions list. The available options include:

* Styling modifications for the rendered Markdown.
* Syntax highlighting theme selection and modification.
* TeX math formulae processing enabling and customization.
* What the hotkey should be.

For Chrome and Firefox, any changes made in the *Markdown Here* Options are automatically synchronized between your other installations of that browser (if you have the sync feature enabled in the browser).

![screenshot of options](https://raw.githubusercontent.com/adam-p/markdown-here/master/store-assets/markdown-here-chrome-options-1.gimp.png)


## Troubleshooting

See the [Troubleshooting wiki page](https://github.com/adam-p/markdown-here/wiki/Troubleshooting).


## Compatibility

See the [Compatibility wiki page](https://github.com/adam-p/markdown-here/wiki/Compatibility).


## Notes and Miscellaneous

* *Markdown Here* uses [Github Flavored Markdown](http://github.github.com/github-flavored-markdown/), with the limitation that GFM special links are not supported ([issue #11](https://github.com/adam-p/markdown-here/issues/11)); nor will they be, as MDH is not Github-specific.

* Available languages for syntax highlighting (and the way they should be written in the fenced code block) can be seen on the [highlight.js demo page](http://softwaremaniacs.org/media/soft/highlight/test.html).

* Images embedded inline in your Markdown will be retained when you "Markdown Toggle". Gmail allows you to put images inline in your email -- this can be much easier than referencing an external image.

* Email signatures are automatically excluded from conversion. Specifically, anything after the semi-standard `''-- ''` (note the trailing space) is left alone.
  * Note that Hotmail and Yahoo do *not* automatically add the `''-- ''` to signatures, so you have to add it yourself.

* The "Markdown Toggle" menu item shows up for more element types than it can correctly render. This is intended to help people realize that they''re not using a rich editor. Otherwise they just don''t see the menu item and don''t know why.

* Styling:
  * The use of browser-specific styles (-moz-, -webkit-) should be avoided. If used, they may not render correctly for people reading the email in a different browser from the one where the email was sent.
  * The use of state-dependent styles (like `a:hover`) don''t work because they don''t match at the time the styles are made explicit. (In email, styles must be explicitly applied to all elements -- stylesheets get stripped.)

* For more tweaky features, visit the [Tips and Tricks](https://github.com/adam-p/markdown-here/wiki/Tips-and-Tricks) section.

## Building the Extension Bundles

```
cd utils
node build.js
```


### Chrome and Opera extension

Create a file with a `.zip` extension containing these files and directories:

```
manifest.json
common/
chrome/
```

### Firefox/Thunderbird extension

Create a file with a `.xpi` extension containing these files and directories:

```
chrome.manifest
install.rdf
common/
firefox/
```

### Safari extension

The browser-specific code is located in the [`markdown-here-safari`](https://github.com/adam-p/markdown-here-safari) project.

Use the Safari Extension Builder.

## Next Steps

See the [issues list](https://github.com/adam-p/markdown-here/issues) and the [Notes Wiki](https://github.com/adam-p/markdown-here/wiki/Development-Notes). All ideas, bugs, plans, complaints, and dreams will end up in one of those two places.

Feel free to create a feature request issue if what you want isn''t already there. If you''d prefer a less formal approach to floating an idea, post to the ["markdown-here" Google Group](https://groups.google.com/forum/?fromgroups=#!forum/markdown-here).

It also takes a fair bit of work to stay up-to-date with the latest changes in all the applications and web sites where Markdown Here works.

## Credits

*Markdown Here* was coded on the shoulders of giants.

* Markdown-to-HTML: [chjj / marked](https://github.com/chjj/marked)
* Syntax highlighting: [isagalaev / highlight.js](https://github.com/isagalaev/highlight.js)
* HTML-to-text: [mtrimpe / jsHtmlToText](https://github.com/mtrimpe/jsHtmlToText)

## Feedback

All bugs, feature requests, pull requests, feedback, etc., are welcome. [Create an issue](https://github.com/adam-p/markdown-here/issues). Or [post to the "markdown-here" Google Group](https://groups.google.com/forum/?fromgroups=#!forum/markdown-here).

## License

### Code

MIT License: http://adampritchard.mit-license.org/ or see [the `LICENSE` file](https://github.com/adam-p/markdown-here/blob/master/LICENSE).

### Logo

Copyright 2015, [Austin Anderson](http://protractor.ninja/). Licensed to Markdown Here under the [MDH contributor license agreement](https://github.com/adam-p/markdown-here/blob/master/CLA-individual.md).

### Other images

[Creative Commons Attribution 3.0 Unported (CC BY 3.0) License](http://creativecommons.org/licenses/by/3.0/)

---

![Dos Equis man says](https://raw.github.com/adam-p/markdown-here/master/store-assets/dos-equis-MDH.jpg)', '2020-08-07 15:38:23');
INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (7, 1, 1, 1, 0, 'Open Source Society University (OSSU)', 'ossu', 'The OSSU curriculum is a complete education in computer science using online materials.
It''s not merely for career training or professional development.
It''s for those who want a proper, *well-rounded* grounding in concepts fundamental to all computing disciplines,
and for those who have the discipline, will, and (most importantly!) good habits to obtain this education largely on their own,
but with support from a worldwide community of fellow learners.', '![Open Source Society University (OSSU)](http://i.imgur.com/kYYCXtC.png)

<h3 align="center">Open Source Society University</h3>
<p align="center">
  Path to a free self-taught education in Computer Science!
</p>
<p align="center">
  <a href="https://github.com/sindresorhus/awesome">
    <img alt="Awesome" src="https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg">
  </a>
  <a href="https://github.com/ossu/computer-science">
	<img alt="Open Source Society University - Computer Science" src="https://img.shields.io/badge/OSSU-computer--science-blue.svg">
  </a>
</p>

# Contents

- [Summary](#summary)
- [Community](#community)
- [Curriculum](#curriculum)
- [Code of conduct](#code-of-conduct)
- [Team](#team)
- [References](#references)

# Summary

The OSSU curriculum is a **complete education in computer science** using online materials.
It''s not merely for career training or professional development.
It''s for those who want a proper, *well-rounded* grounding in concepts fundamental to all computing disciplines,
and for those who have the discipline, will, and (most importantly!) good habits to obtain this education largely on their own,
but with support from a worldwide community of fellow learners.

It is designed according to the degree requirements of undergraduate computer science majors, minus general education (non-CS) requirements,
as it is assumed most of the people following this curriculum are already educated outside the field of CS.
The courses themselves are among the very best in the world, often coming from Harvard, Princeton, MIT, etc.,
but specifically chosen to meet the following criteria.

**Courses must**:
- Be open for enrollment
- Run regularly (ideally in self-paced format, otherwise running at least once a month or so)
- Fulfill the [academic requirements](REQUIREMENTS.md) of OSSU
- Fit neatly into the progression of the curriculum with respect to topics and difficulty level
- Be of generally high quality in teaching materials and pedagogical principles

When no course meets the above criteria, the coursework is supplemented with a book.
When there are courses or books that don''t fit into the curriculum but are otherwise of high quality,
they belong in [extras/courses](extras/courses.md) or [extras/readings](extras/readings.md).

**Organization**. The curriculum is designed as follows:
- *Intro CS*: for students to try out CS and see if it''s right for them
- *Core CS*: corresponds roughly to the first three years of a computer science curriculum, taking classes that all majors would be required to take
- *Advanced CS*: corresponds roughly to the final year of a computer science curriculum, taking electives according to the student''s interests
- *Final Project*: a project for students to validate, consolidate, and display their knowledge, to be evaluated by their peers worldwide


**Duration**. It is possible to finish Core CS within about 2 years if you plan carefully and devote roughly 18-22 hours/week to your studies.
Courses in Core CS should be taken linearly if possible, but since a perfectly linear progression is rarely possible,
each class''s prerequisites are specified so that you can design a logical but non-linear progression
based on the class schedules and your own life plans.

**Cost**. All or nearly all course material is available for free. However, some courses may charge money for assignments/tests/projects to be graded.
Note that Coursera offers [financial aid](https://learner.coursera.help/hc/en-us/articles/209819033-Apply-for-Financial-Aid).

Decide how much or how little to spend based on your own time and budget;
just remember that you can''t purchase success!

**Process**. Students can work through the curriculum alone or in groups, in order or out of order.
- For grouping up, please use the [cohorts repository](https://github.com/ossu/cohorts) to find or create a cohort suited to you.
- We recommend doing all courses in Core CS, only skipping a course when you are certain that you''ve already learned the material previously.
- For simplicity, we recommend working through courses (especially Core CS) in order from top to bottom, as they have already been [topologically sorted](https://en.wikipedia.org/wiki/Topological_sorting) by their prerequisites.
- Courses in Advanced CS are electives. Choose one subject (e.g. Advanced programming) you want to become an expert in and take all the courses under that heading. You can also create your own custom subject, but we recommend getting validation from the community on the subject you choose.

**Content policy**. If you plan on showing off some of your coursework publicly, you must share only files that you are allowed to.
*Do NOT disrespect the code of conduct* that you signed in the beginning of each course!

**[How to contribute](CONTRIBUTING.md)**

**[Getting help](HELP.md)** (Details about our FAQ and chatroom)

# Community

- We have a chat room! This should be your first stop to talk with other OSSU students. Why don''t you introduce yourself right now? [Join the chat in Gitter](https://gitter.im/open-source-society/computer-science) [![Join the chat at https://gitter.im/open-source-society/computer-science](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/open-source-society/computer-science?utm_campaign=pr-badge&utm_content=badge&utm_medium=badge&utm_source=badge)
- You can also interact through [GitHub issues](https://github.com/ossu/computer-science/issues). If there is a problem with a course, or a change needs to be made to the curriculum, this is the place to start the conversation. Read more [here](CONTRIBUTING.md).
- Add **Open Source Society University** to your [Linkedin](https://www.linkedin.com/school/11272443/) profile!
- Note: There is an unmaintained and deprecated firebase app that you might find when searching OSSU. You can safely ignore it. Read more in the [FAQ](./FAQ.md#why-is-the-firebase-ossu-app-different-or-broken).

# Curriculum

**Curriculum version**: `8.0.0` (see [CHANGELOG](CHANGELOG.md))

- [Prerequisites](#prerequisites)
- [Intro CS](#intro-cs)
  - [Introduction to Programming](#introduction-to-programming)
  - [Introduction to Computer Science](#introduction-to-computer-science)
- [Core CS](#core-cs)
  - [Core programming](#core-programming)
  - [Core math](#core-math)
  - [Core systems](#core-systems)
  - [Core theory](#core-theory)
  - [Core applications](#core-applications)
  - [Core security](#core-security)
- [Advanced CS](#advanced-cs)
  - [Advanced programming](#advanced-programming)
  - [Advanced systems](#advanced-systems)
  - [Advanced theory](#advanced-theory)
  - [Advanced applications](#advanced-applications)
- [Final project](#final-project)

---

## Prerequisites

- [Core CS](#core-cs) assumes the student has already taken [high school math](https://www.khanacademy.org/math/high-school-math), including algebra, geometry, and pre-calculus.
- [Advanced CS](#advanced-cs) assumes the student has already taken the entirety of Core CS
and is knowledgeable enough now to decide which electives to take.
- Note that [Advanced systems](#advanced-systems) assumes the student has taken a basic physics course (e.g. AP Physics in high school).

## Intro CS

### Introduction to Programming

If you''ve never written a for-loop, or don''t know what a string is in programming, start here. Choose one of the two course series below. Either one will give you an introduction to programming that assumes no prior knowledge.

Trying to decide between them?

_Python for Everyone_ will introduce you to a popular language and will quickly move to practical programming tasks - using web APIs and databases. This will give you a taste of what many professional developers do.

_Fundamentals of Computing_ will also start by introducing you to Python. It then moves on to give an introduction to academic Computer Science topics, like sorting and recursion. This will give you a taste of what the following courses will be like. (Students who complete _Fundamentals of Computing_ can skip Intro to Computer Science and begin Core CS.)

**Topics covered**:
`simple programs`
`simple data structures`

Courses | Effort | Prerequisites
:-- | :--: | :--:
[Python for Everyone](https://www.coursera.org/specializations/python) ([alt](https://www.py4e.com/)) | 58 hours | none
[Fundamentals of Computing](https://www.coursera.org/specializations/computer-fundamentals) | 138 hours | high school mathematics

### Introduction to Computer Science

This course will introduce you to the world of computer science. Students who have been introduced to programming, either from the courses above or through study elsewhere, should take this course for a flavor of the material to come. If you finish the course wanting more, Computer Science is likely for you!

**Topics covered**:
`computation`
`imperative programming`
`basic data structures and algorithms`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Introduction to Computer Science and Programming using Python](https://www.edx.org/course/introduction-computer-science-mitx-6-00-1x-10) ([alt](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-0001-introduction-to-computer-science-and-programming-in-python-fall-2016/)) | 9 weeks | 15 hours/week | high school algebra

## Core CS

All coursework under Core CS is **required**, unless otherwise indicated.

### Core programming
**Topics covered**:
`functional programming`
`design for testing`
`program requirements`
`common design patterns`
`unit testing`
`object-oriented design`
`Java`
`static typing`
`dynamic typing`
`ML-family languages (via Standard ML)`
`Lisp-family languages (via Racket)`
`Ruby`
`and more`

The How to Code courses are based on the textbook [How to Design Programs](https://htdp.org/2003-09-26/). The First Edition is available for free online and includes problem sets and solutions. Students are encouraged to do these assignments.

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[How to Code - Simple Data](https://www.edx.org/course/how-code-simple-data-ubcx-htc1x) | 7 weeks | 8-10 hours/week | none
[How to Code - Complex Data](https://www.edx.org/course/how-code-complex-data-ubcx-htc2x) | 6 weeks | 8-10 hours/week | How to Code: Simple Data
[Programming Languages, Part A](https://www.coursera.org/learn/programming-languages) | 4 weeks | 8-16 hours/week | recommended: Java, C
[Programming Languages, Part B](https://www.coursera.org/learn/programming-languages-part-b) | 3 weeks | 8-16 hours/week | Programming Languages, Part A
[Programming Languages, Part C](https://www.coursera.org/learn/programming-languages-part-c) | 3 weeks | 8-16 hours/week | Programming Languages, Part B

#### Readings
- **Required** to learn about monads, laziness, purity: [Learn You a Haskell for a Great Good!](http://learnyouahaskell.com/)
  - **Note**: probably the best resource to learn Haskell: [Haskell Programming from First Principles](http://haskellbook.com/) `paid`
- **Required**, to learn about logic programming, backtracking, unification: [Learn Prolog Now!](http://lpn.swi-prolog.org/lpnpage.php?pageid=top)

### Math Electives
**Students must choose one of the following topics**: calculus, linear algebra, logic, or probability.

#### Calculus
Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Calculus 1A: Differentiation](https://www.edx.org/course/calculus-1a-differentiation) | 13 weeks | 6-10 hours/week | pre-calculus
[Calculus 1B: Integration](https://www.edx.org/course/calculus-1b-integration) | 13 weeks | 5-10 hours/week | Calculus 1A
[Calculus 1C: Coordinate Systems & Infinite Series](https://www.edx.org/course/calculus-1c-coordinate-systems-infinite-series) | 13 weeks | 5-10 hours/week | Calculus 1B

#### Linear Algebra
Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Essence of Linear Algebra](https://www.youtube.com/playlist?list=PLZHQObOWTQDPD3MizzM2xVFitgF8hE_ab) | - | - | pre-calculus
[Linear Algebra - Foundations to Frontiers](https://www.edx.org/course/linear-algebra-foundations-to-frontiers-0) ([alt](http://ulaff.net/)) | 15 weeks | 8 hours/week | Essence of Linear Algebra

#### Logic
Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Introduction to Logic](https://www.coursera.org/learn/logic-introduction) | 10 weeks | 4-8 hours/week | set theory

#### Probability
Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Introduction to Probability - The Science of Uncertainty](https://www.edx.org/course/introduction-probability-science-mitx-6-041x-2) | 18 weeks | 12 hours/week | Multivariable Calculus

### Core Math
In addition to their math elective, students must complete the following course on discrete mathematics.

**Topics covered**:
`discrete mathematics`
`mathematical proofs`
`basic statistics`
`O-notation`
`discrete probability`
`and more`

Courses | Duration | Effort | Notes | Prerequisites
:-- | :--: | :--: | :--: | :--:
[Mathematics for Computer Science](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-042j-mathematics-for-computer-science-spring-2015/index.htm) | 13 weeks | 5 hours/week | An alternate version with solutions to the problem sets is [here](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-042j-mathematics-for-computer-science-fall-2005/assignments/). Students struggling can consider the [Discrete Mathematics Specialization](https://www.coursera.org/specializations/discrete-mathematics) first. It is more interactive but less comprehensive, and costs money to unlock full interactivity. | Calculus 1C

### Core systems

**Topics covered**:
`procedural programming`
`manual memory management`
`boolean algebra`
`gate logic`
`memory`
`computer architecture`
`assembly`
`machine language`
`virtual machines`
`high-level languages`
`compilers`
`operating systems`
`network protocols`
`and more`

Courses | Duration | Effort | Additional Text / Assignments| Prerequisites
:-- | :--: | :--: | :--: | :--:
[Introduction to Computer Science - CS50](https://www.edx.org/course/introduction-computer-science-harvardx-cs50x#!) ([alt](https://cs50.harvard.edu/)) | 12 weeks | 10-20 hours/week | After the sections on C, skip to the next course. [Why?](FAQ.md#why-do-you-recommend-skipping-the-second-half-of-cs50) | introductory programming
[Build a Modern Computer from First Principles: From Nand to Tetris](https://www.coursera.org/learn/build-a-computer) ([alt](http://www.nand2tetris.org/)) | 6 weeks | 7-13 hours/week | - | C-like programming language
[Build a Modern Computer from First Principles: Nand to Tetris Part II ](https://www.coursera.org/learn/nand2tetris2) | 6 weeks | 12-18 hours/week | - | one of [these programming languages](https://user-images.githubusercontent.com/2046800/35426340-f6ce6358-026a-11e8-8bbb-4e95ac36b1d7.png), From Nand to Tetris Part I
[Introduction to Computer Networking](https://www.youtube.com/playlist?list=PLEAYkSg4uSQ2dr0XO_Nwa5OcdEcaaELSG)| 8 weeks | 4?12 hours/week | [Assignment 1](https://github.com/PrincetonUniversity/COS461-Public/tree/master/assignments/assignment1)<br>[Assignment 2](https://www.scs.stanford.edu/10au-cs144/lab/reliable/reliable.html)<br>[Assignment 3](https://nptel.ac.in/content/storage2/courses/106105080/pdf/M2L7.pdf)<br>[Assignment 4](http://www-net.cs.umass.edu/wireshark-labs/Wireshark_TCP_v7.0.pdf) | algebra, probability, basic CS
[ops-class.org - Hack the Kernel](https://www.ops-class.org/) | 15 weeks | 6 hours/week | Replace course textbook with [Operating Systems: Three Easy Pieces](http://pages.cs.wisc.edu/~remzi/OSTEP/) | algorithms

### Core theory

**Topics covered**:
`divide and conquer`
`sorting and searching`
`randomized algorithms`
`graph search`
`shortest paths`
`data structures`
`greedy algorithms`
`minimum spanning trees`
`dynamic programming`
`NP-completeness`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Divide and Conquer, Sorting and Searching, and Randomized Algorithms](https://www.coursera.org/learn/algorithms-divide-conquer) | 4 weeks | 4-8 hours/week | any programming language, Mathematics for Computer Science
[Graph Search, Shortest Paths, and Data Structures](https://www.coursera.org/learn/algorithms-graphs-data-structures) | 4 weeks | 4-8 hours/week | Divide and Conquer, Sorting and Searching, and Randomized Algorithms
[Greedy Algorithms, Minimum Spanning Trees, and Dynamic Programming](https://www.coursera.org/learn/algorithms-greedy) | 4 weeks | 4-8 hours/week | Graph Search, Shortest Paths, and Data Structures
[Shortest Paths Revisited, NP-Complete Problems and What To Do About Them](https://www.coursera.org/learn/algorithms-npcomplete) | 4 weeks | 4-8 hours/week | Greedy Algorithms, Minimum Spanning Trees, and Dynamic Programming

### Core Security
**Topics covered**
`Confidentiality, Integrity, Availability`
`Secure Design`
`Defensive Programming`
`Threats and Attacks`
`Network Security`
`Cryptography`
`and more`

Note: **_These courses are provisionally recommended_**. There is an open [Request For Comment](https://github.com/ossu/computer-science/issues/639) on security course selection. Contributors are encouraged to compare the various courses in the RFC and offer feedback.

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Information Security: Context and Introduction](https://www.coursera.org/learn/information-security-data) | 5 weeks | 3 hours/week | -
[Principles of Secure Coding](https://www.coursera.org/learn/secure-coding-principles)| 4 weeks | 4 hours/week | -
[Identifying Security Vulnerabilities](https://www.coursera.org/learn/identifying-security-vulnerabilities) | 4 weeks | 4 hours/week | -

Choose **one** of the following:
Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Identifying Security Vulnerabilities in C/C++Programming](https://www.coursera.org/learn/identifying-security-vulnerabilities-c-programming) | 4 weeks | 5 hours/week | -
[Exploiting and Securing Vulnerabilities in Java Applications](https://www.coursera.org/learn/exploiting-securing-vulnerabilities-java-applications) | 4 weeks | 5 hours/week | -

### Core applications

**Topics covered**:
`Agile methodology`
`REST`
`software specifications`
`refactoring`
`relational databases`
`transaction processing`
`data modeling`
`neural networks`
`supervised learning`
`unsupervised learning`
`OpenGL`
`raytracing`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Relational Database Systems](https://www.coursera.org/learn/relational-database)| 6 weeks | 3 hours/week | -
[Machine Learning](https://www.coursera.org/learn/machine-learning)| 11 weeks | 4-6 hours/week | linear algebra
[Computer Graphics](https://www.edx.org/course/computer-graphics-uc-san-diegox-cse167x)| 6 weeks | 12 hours/week | C++ or Java, linear algebra
[Software Engineering: Introduction](https://www.edx.org/course/software-engineering-introduction-ubcx-softeng1x) | 6 weeks | 8-10 hours/week | Software Construction - Object-Oriented Design
[Software Development Capstone Project](https://www.edx.org/course/software-development-capstone-project-ubcx-softengprjx) | 6-7 weeks | 8-10 hours/week | Software Engineering: Introduction

## Advanced CS

After completing **every required course** in Core CS, students should choose a subset of courses from Advanced CS based on interest.
Not every course from a subcategory needs to be taken.
But students should take *every* course that is relevant to the field they intend to go into.

The Advanced CS study should then end with one of the Specializations under [Advanced applications](#advanced-applications).
A Specialization''s Capstone, if taken, may act as the [Final project](#final-project), if permitted by the Honor Code of the course.
If not, or if a student chooses not to take the Capstone, then a separate Final project will need to be done to complete this curriculum.

### Advanced programming

**Topics covered**:
`debugging theory and practice`
`goal-oriented programming`
`GPU programming`
`CUDA`
`parallel computing`
`object-oriented analysis and design`
`UML`
`large-scale software architecture and design`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Introduction to Parallel Programming](https://classroom.udacity.com/courses/cs344) ([alt](https://www.youtube.com/playlist?list=PLGvfHSgImk4aweyWlhBXNF6XISY3um82_)) ([HW](https://colab.research.google.com/github/depctg/udacity-cs344-colab))| 12 weeks | - | C, algorithms
[Compilers](https://www.edx.org/course/compilers) ([alt](https://www.youtube.com/playlist?list=PLDcmCgguL9rxPoVn2ykUFc8TOpLyDU5gx))| 9 weeks | 6-8 hours/week | none
[Software Debugging](https://www.udacity.com/course/software-debugging--cs259)| 8 weeks | 6 hours/week | Python, object-oriented programming
[Software Testing](https://www.udacity.com/course/software-testing--cs258) | 4 weeks | 6 hours/week | Python, programming experience
[LAFF - On Programming for Correctness](https://www.edx.org/course/laff-on-programming-for-correctness) | 7 weeks | 6 hours/week | linear algebra
[Software Architecture & Design](https://www.udacity.com/course/software-architecture-design--ud821)| 8 weeks | 6 hours/week | software engineering in Java

### Advanced systems

**Topics covered**:
`digital signaling`
`combinational logic`
`CMOS technologies`
`sequential logic`
`finite state machines`
`processor instruction sets`
`caches`
`pipelining`
`virtualization`
`parallel processing`
`virtual memory`
`synchronization primitives`
`system call interface`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Electricity and Magnetism, Part 1](https://www.edx.org/course/electricity-magnetism-part-1-ricex-phys102-1x-0)<sup>1</sup> | 7 weeks | 8-10 hours/week | calculus, basic mechanics
[Electricity and Magnetism, Part 2](https://www.edx.org/course/electricity-magnetism-part-2-ricex-phys102-2x-0) | 7 weeks | 8-10 hours/week | Electricity and Magnetism, Part 1
[Computation Structures 1: Digital Circuits](https://www.edx.org/course/computation-structures-part-1-digital-mitx-6-004-1x-0) | 10 weeks | 6 hours/week | electricity, magnetism
[Computation Structures 2: Computer Architecture](https://www.edx.org/course/computation-structures-2-computer-mitx-6-004-2x) | 10 weeks | 6 hours/week | Computation Structures 1
[Computation Structures 3: Computer Organization](https://www.edx.org/course/computation-structures-3-computer-mitx-6-004-3x-0) | 10 weeks | 6 hours/week | Computation Structures 2

**<sup>1</sup> Note**:
These courses assume knowledge of basic physics.
([Why?](FAQ.md#why-is-the-curriculum-missing-some-pre-requisites))
If you are struggling, you can find a physics MOOC or utilize the materials from Khan Academy:
[Khan Academy - Physics](https://www.khanacademy.org/science/physics)

### Advanced theory

**Topics covered**:
`formal languages`
`Turing machines`
`computability`
`event-driven concurrency`
`automata`
`distributed shared memory`
`consensus algorithms`
`state machine replication`
`computational geometry theory`
`propositional logic`
`relational logic`
`Herbrand logic`
`concept lattices`
`game trees`
`and more`

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Theory of Computation](http://aduni.org/courses/theory/index.php?view=cw) ([Lectures](https://www.youtube.com/playlist?list=PLTke5lHMAdSNmi57H0DOTzClHPK6UwSTN)) | 8 weeks | 10 hours/week | discrete mathematics, logic, algorithms
[Computational Geometry](https://www.edx.org/course/computational-geometry-tsinghuax-70240183x) | 16 weeks | 8 hours/week | algorithms, C++
[Introduction to Formal Concept Analysis](https://www.coursera.org/learn/formal-concept-analysis) | 6 weeks | 4-6 hours/week | logic, probability
[Game Theory](https://www.coursera.org/learn/game-theory-1) | 8 weeks | 3 hours/week | mathematical thinking, probability, calculus

### Advanced applications

These Coursera Specializations all end with a Capstone project.
Depending on the course, you may be able to utilize the Capstone as your Final Project for this Computer Science curriculum.
Note that doing a Specialization with the Capstone at the end always costs money.
So if you don''t wish to spend money or use the Capstone as your Final, it may be possible to take the courses in the Specialization for free by manually searching for them, but not all allow this.

Courses | Duration | Effort | Prerequisites
:-- | :--: | :--: | :--:
[Modern Robotics (Specialization)](https://www.coursera.org/specializations/modernrobotics) | 26 weeks | 2-5 hours/week | freshman-level physics, linear algebra, calculus, linear ordinary differential equations
[Data Mining (Specialization)](https://www.coursera.org/specializations/data-mining) | 30 weeks | 2-5 hours/week | machine learning
[Big Data (Specialization)](https://www.coursera.org/specializations/big-data) | 30 weeks | 3-5 hours/week | none
[Internet of Things (Specialization)](https://www.coursera.org/specializations/internet-of-things) | 30 weeks | 1-5 hours/week | strong programming
[Cloud Computing (Specialization)](https://www.coursera.org/specializations/cloud-computing) | 30 weeks | 2-6 hours/week | C++ programming
[Full Stack Web Development (Specialization)](https://www.coursera.org/specializations/full-stack) | 27 weeks | 2-6 hours/week | programming, databases
[Data Science (Specialization)](https://www.coursera.org/specializations/jhu-data-science) | 43 weeks | 1-6 hours/week | none
[Functional Programming in Scala (Specialization)](https://www.coursera.org/specializations/scala) | 29 weeks | 4-5 hours/week | One year programming experience
[Game Design and Development (Specialization)](https://www.coursera.org/specializations/game-development) | 6 months | 5 hours/week | programming, interactive design

## Final project

OSS University is **project-focused**.
You are encouraged to do the assignments and exams for each course, but what really matters is whether you can *use* your knowledge to solve a real-world problem.

After you''ve gotten through all of Core CS and the parts of Advanced CS relevant to you, you should think about a problem that you can solve using the knowledge you''ve acquired.
Not only does real project work look great on a resume, but the project will also *validate* and *consolidate* your knowledge.
You can create something entirely new, or you can find an existing project that needs help via websites like
[CodeTriage](https://www.codetriage.com/)
or
[First Timers Only](http://www.firsttimersonly.com/).

Another option is using the Capstone project from taking one of the Specializations in [Advanced applications](#advanced-applications);
whether or not this makes sense depends on the course, the project, and whether or not the course''s Honor Code permits you to display your work publicly.
In some cases, it may not be permitted;
do **not** violate your course''s Honor Code!

Put the OSSU-CS badge in the README of your repository!
[![Open Source Society University - Computer Science](https://img.shields.io/badge/OSSU-computer--science-blue.svg)](https://github.com/ossu/computer-science)

- Markdown: `[![Open Source Society University - Computer Science](https://img.shields.io/badge/OSSU-computer--science-blue.svg)](https://github.com/ossu/computer-science)`
- HTML: `<a href="https://github.com/ossu/computer-science"><img alt="Open Source Society University - Computer Science" src="https://img.shields.io/badge/OSSU-computer--science-blue.svg"></a>`

### Evaluation

Upon completing your final project, submit your project''s information to [PROJECTS](PROJECTS.md)
via a pull request and use our [community](#community) channels to announce it to your fellow students.

Your peers and mentors from OSSU will then informally evaluate your project.
You will not be "graded" in the traditional sense ? everyone has their own measurements for what they consider a success.
The purpose of the evaluation is to act as your first announcement to the world that you are a computer scientist
and to get experience listening to feedback ? both positive and negative ? and taking it in stride.

The final project evaluation has a second purpose: to evaluate whether OSSU,
through its community and curriculum, is successful in its mission to guide independent learners in obtaining a world-class computer science education.

### Cooperative work

You can create this project alone or with other students!
**We love cooperative work**!
Use our [channels](#community) to communicate with other fellows to combine and create new projects!

### Which programming languages should I use?

My friend, here is the best part of liberty!
You can use **any** language that you want to complete the final project.

The important thing is to **internalize** the core concepts and to be able to use them with whatever tool (programming language) that you wish.

## Congratulations

After completing the requirements of the curriculum above, you will have completed the equivalent of a full bachelor''s degree in Computer Science. Congratulations!

What is next for you? The possibilities are boundless and overlapping:

- Look for a job as a developer!
- Check out the [readings](extras/readings.md) for classic books you can read that will sharpen your skills and expand your knowledge.
- Join a local developer meetup (e.g. via [meetup.com](https://www.meetup.com/)).
- Pay attention to emerging technologies in the world of software development:
  + Explore the **actor model** through [Elixir](http://elixir-lang.org/), a new functional programming language for the web based on the battle-tested Erlang Virtual Machine!
  + Explore **borrowing and lifetimes** through [Rust](https://www.rust-lang.org/), a systems language which achieves memory- and thread-safety without a garbage collector!
  + Explore **dependent type systems** through [Idris](https://www.idris-lang.org/), a new Haskell-inspired language with unprecedented support for type-driven development.

![keep learning](http://i.imgur.com/REQK0VU.jpg)

# Code of conduct
[OSSU''s code of conduct](https://github.com/ossu/code-of-conduct).

## How to show your progress

1. Create an account in [Trello](https://trello.com/).
1. Copy [this](https://trello.com/b/7NIfi40X) board to your personal account.
See how to copy a board [here](https://help.trello.com/article/802-copying-cards-lists-or-boards).

Now that you have a copy of our official board, you just need to pass the cards to the `Doing` column or `Done` column as you progress in your study.

We also have **labels** to help you have more control through the process.
The meaning of each of these labels is:

- `Main Curriculum`: cards with that label represent courses that are listed in our curriculum.
- `Extra Resources`: cards with that label represent courses that were added by the student.
- `Doing`: cards with that label represent courses the student is current doing.
- `Done`: cards with that label represent courses finished by the student.
Those cards should also have the link for at least one project/article built with the knowledge acquired in such course.
- `Section`: cards with that label represent the section that we have in our curriculum.
Those cards with the `Section` label are only to help the organization of the Done column.
You should put the *Course''s cards* below its respective *Section''s card*.

The intention of this board is to provide our students a way to track their progress, and also the ability to show their progress through a public page for friends, family, employers, etc.
You can change the status of your board to be *public* or *private*.

# Team

* **[Eric Douglas](https://github.com/ericdouglas)**: founder of OSSU
* **[hanjiexi](https://github.com/hanjiexi)**: lead technical maintainer
* **[waciumawanjohi](https://github.com/waciumawanjohi)**: lead academic maintainer
* **[Contributors](https://github.com/ossu/computer-science/graphs/contributors)**

# References

- [Google - Guide for Technical Development](https://www.google.com/about/careers/students/guide-to-technical-development.html)
- [Coursera](https://www.coursera.org/)
- [edX](https://www.edx.org)
- [Udacity](https://www.udacity.com/)
- [Stanford University](https://lagunita.stanford.edu/)
- [Carnegie Mellon University: Computer Science Major Requirements](https://www.csd.cs.cmu.edu/academics/undergraduate/requirements)
- [MIT Open Courseware](http://ocw.mit.edu/courses/#electrical-engineering-and-computer-science)
- [Teach Yourself Computer Science](https://teachyourselfcs.com/)
- [Obtaining a Thorough CS Background Online](http://spin.atomicobject.com/2015/05/15/obtaining-thorough-cs-background-online/)', '2020-08-08 15:38:26');
INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (8, 1, 1, 1, 0, 'Jekyll
', 'jekyll', 'Jekyll is a simple, blog-aware, static site generator perfect for personal, project, or organization sites. Think of it like a file-based CMS, without all the complexity. Jekyll takes your content, renders Markdown and Liquid templates, and spits out a complete, static website ready to be served by Apache, Nginx or another web server. Jekyll is the engine behind GitHub Pages, which you can use to host sites right from your GitHub repositories.', '# [Jekyll](https://jekyllrb.com/)

[![Gem Version](https://img.shields.io/gem/v/jekyll.svg)][ruby-gems]
[![Linux Build Status](https://img.shields.io/travis/jekyll/jekyll/master.svg?label=Linux%20build)][travis]
[![Windows Build status](https://img.shields.io/appveyor/ci/jekyll/jekyll/master.svg?label=Windows%20build)][appveyor]
[![Maintainability](https://api.codeclimate.com/v1/badges/8ba0cb5b17bb9848e128/maintainability)][codeclimate]
[![Test Coverage](https://api.codeclimate.com/v1/badges/8ba0cb5b17bb9848e128/test_coverage)][coverage]
[![Security](https://hakiri.io/github/jekyll/jekyll/master.svg)][hakiri]
[![Backers on Open Collective](https://opencollective.com/jekyll/backers/badge.svg)](#backers)
[![Sponsors on Open Collective](https://opencollective.com/jekyll/sponsors/badge.svg)](#sponsors)

[ruby-gems]: https://rubygems.org/gems/jekyll
[codeclimate]: https://codeclimate.com/github/jekyll/jekyllyou
[coverage]: https://codeclimate.com/github/jekyll/jekyll/coverage
[hakiri]: https://hakiri.io/github/jekyll/jekyll/master
[travis]: https://travis-ci.org/jekyll/jekyll
[appveyor]: https://ci.appveyor.com/project/jekyll/jekyll/branch/master

Jekyll is a simple, blog-aware, static site generator perfect for personal, project, or organization sites. Think of it like a file-based CMS, without all the complexity. Jekyll takes your content, renders Markdown and Liquid templates, and spits out a complete, static website ready to be served by Apache, Nginx or another web server. Jekyll is the engine behind [GitHub Pages](https://pages.github.com), which you can use to host sites right from your GitHub repositories.

## Philosophy

Jekyll does what you tell it to do ? no more, no less. It doesn''t try to outsmart users by making bold assumptions, nor does it burden them with needless complexity and configuration. Put simply, Jekyll gets out of your way and allows you to concentrate on what truly matters: your content.

See: https://jekyllrb.com/philosophy

## Getting Started

* [Install](https://jekyllrb.com/docs/installation/) the gem
* Read up about its [Usage](https://jekyllrb.com/docs/usage/) and [Configuration](https://jekyllrb.com/docs/configuration/)
* Take a gander at some existing [Sites](https://github.com/jekyll/jekyll/wiki/sites)
* [Fork](https://github.com/jekyll/jekyll/fork) and [Contribute](https://jekyllrb.com/docs/contributing/) your own modifications
* Have questions? Check out our official forum community [Jekyll Talk](https://talk.jekyllrb.com/) or [`#jekyll` on irc.freenode.net](https://botbot.me/freenode/jekyll/)

## Diving In

* [Migrate](http://import.jekyllrb.com/docs/home/) from your previous system
* Learn how [Front Matter](https://jekyllrb.com/docs/front-matter/) works
* Put information on your site with [Variables](https://jekyllrb.com/docs/variables/)
* Customize the [Permalinks](https://jekyllrb.com/docs/permalinks/) your posts are generated with
* Use the built-in [Liquid Extensions](https://jekyllrb.com/docs/templates/) to make your life easier
* Use custom [Plugins](https://jekyllrb.com/docs/plugins/) to generate content specific to your site
* Watch [video tutorials from Giraffe Academy](https://jekyllrb.com/tutorials/video-walkthroughs/)

## Need help?

If you don''t find the answer to your problem in our [docs](https://jekyllrb.com/docs/), or in the [troubleshooting section](https://jekyllrb.com/docs/troubleshooting/), ask the [community](https://jekyllrb.com/docs/community/) for help.

## Code of Conduct

In order to have a more open and welcoming community, Jekyll adheres to a
[code of conduct](CODE_OF_CONDUCT.markdown) adapted from the Ruby on Rails code of
conduct.

Please adhere to this code of conduct in any interactions you have in the
Jekyll community. It is strictly enforced on all official Jekyll
repositories, websites, and resources. If you encounter someone violating
these terms, please let one of our [core team members](https://jekyllrb.com/team/#core-team) know and we will address it as soon as possible.

## Credits

### Sponsors

Support this project by becoming a sponsor. Your logo will show up in this README with a link to your website. [Become a sponsor!](https://opencollective.com/jekyll#sponsor)

<a href="https://opencollective.com/jekyll/sponsor/0/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/0/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/1/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/1/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/2/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/2/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/3/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/3/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/4/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/4/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/5/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/5/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/6/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/6/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/7/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/7/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/8/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/8/avatar.svg" /></a>
<a href="https://opencollective.com/jekyll/sponsor/9/website" target="_blank"><img src="https://opencollective.com/jekyll/sponsor/9/avatar.svg" /></a>

### Contributors

This project exists thanks to all the people who contribute.
<a href="../../graphs/contributors"><img src="https://opencollective.com/jekyll/contributors.svg?width=890&button=false" /></a>

### Backers

Thank you to all our backers! ÃÂÃÂ°ÃÂÃÂÃÂÃÂÃÂÃÂ [Become a backer](https://opencollective.com/jekyll#backer)

<a href="https://opencollective.com/jekyll#backers" target="_blank"><img src="https://opencollective.com/jekyll/backers.svg?width=890" /></a>

## License

See the [LICENSE](https://github.com/jekyll/jekyll/blob/master/LICENSE) file.', '2020-08-09 15:38:27');
INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (11, 1, 1, 1, 0, 'Git', 'git', 'Git is a fast, scalable, distributed revision control system with an
unusually rich command set that provides both high-level operations
and full access to internals.

Git is an Open Source project covered by the GNU General Public
License version 2 (some parts of it are under different licenses,
compatible with the GPLv2). It was originally written by Linus
Torvalds with help of a group of hackers around the net.', '[![Build Status](https://dev.azure.com/git/git/_apis/build/status/git.git)](https://dev.azure.com/git/git/_build/latest?definitionId=11)

Git - fast, scalable, distributed revision control system
=========================================================

Git is a fast, scalable, distributed revision control system with an
unusually rich command set that provides both high-level operations
and full access to internals.

Git is an Open Source project covered by the GNU General Public
License version 2 (some parts of it are under different licenses,
compatible with the GPLv2). It was originally written by Linus
Torvalds with help of a group of hackers around the net.

Please read the file [INSTALL][] for installation instructions.

Many Git online resources are accessible from <https://git-scm.com/>
including full documentation and Git related tools.

See [Documentation/gittutorial.txt][] to get started, then see
[Documentation/giteveryday.txt][] for a useful minimum set of commands, and
`Documentation/git-<commandname>.txt` for documentation of each command.
If git has been correctly installed, then the tutorial can also be
read with `man gittutorial` or `git help tutorial`, and the
documentation of each command with `man git-<commandname>` or `git help
<commandname>`.

CVS users may also want to read [Documentation/gitcvs-migration.txt][]
(`man gitcvs-migration` or `git help cvs-migration` if git is
installed).

The user discussion and development of Git take place on the Git
mailing list -- everyone is welcome to post bug reports, feature
requests, comments and patches to git@vger.kernel.org (read
[Documentation/SubmittingPatches][] for instructions on patch submission).
To subscribe to the list, send an email with just "subscribe git" in
the body to majordomo@vger.kernel.org. The mailing list archives are
available at <https://lore.kernel.org/git/>,
<http://marc.info/?l=git> and other archival sites.

Issues which are security relevant should be disclosed privately to
the Git Security mailing list <git-security@googlegroups.com>.

The maintainer frequently sends the "What''s cooking" reports that
list the current status of various development topics to the mailing
list.  The discussion following them give a good reference for
project status, development direction and remaining tasks.

The name "git" was given by Linus Torvalds when he wrote the very
first version. He described the tool as "the stupid content tracker"
and the name as (depending on your mood):

 - random three-letter combination that is pronounceable, and not
   actually used by any common UNIX command.  The fact that it is a
   mispronunciation of "get" may or may not be relevant.
 - stupid. contemptible and despicable. simple. Take your pick from the
   dictionary of slang.
 - "global information tracker": you''re in a good mood, and it actually
   works for you. Angels sing, and a light suddenly fills the room.
 - "goddamn idiotic truckload of sh*t": when it breaks

[INSTALL]: INSTALL
[Documentation/gittutorial.txt]: Documentation/gittutorial.txt
[Documentation/giteveryday.txt]: Documentation/giteveryday.txt
[Documentation/gitcvs-migration.txt]: Documentation/gitcvs-migration.txt
[Documentation/SubmittingPatches]: Documentation/SubmittingPatches', '2020-08-10 15:38:27');
INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (12, 1, 1, 1, 0, 'Pywalfox', 'pywalfox', 'Pywalfox is an addon used to theme Firefox using your pywal colors.
- Customizable colors
- Uses the Firefox Theme API (no CSS required!)
- Automatic theming for DuckDuckGo (Optional)
- Custom CSS with bold text, styled dropdowns, etc. (Optional)
- Update the browser theme using the addon and/or your terminal', '<img src="https://i.imgur.com/tZybQsU.gif"/>

# Pywalfox

Pywalfox is an addon used to theme Firefox using your pywal colors.
- Customizable colors
- Uses the Firefox Theme API (no CSS required!)
- Automatic theming for DuckDuckGo (Optional)
- Custom CSS with bold text, styled dropdowns, etc. (Optional)
- Update the browser theme using the addon and/or your terminal

You can download the Firefox Addon here: https://addons.mozilla.org/en-US/firefox/addon/pywalfox/

### Warning
To use this addon, you must install a script on your computer. The script will be ran by Firefox upon launch and will handle fetching your pywal colors. As of now, Pywalfox supports only Linux.

### Requirements
- Python (both 2.7.x and 3.x versions are supported)
- pywal
- Firefox
- Linux

### Installation
1. `git clone https://github.com/Frewacom/Pywalfox.git`
2. `cd Pywalfox`
3. `bash setup.sh`

If the setup is successfull, it should look something like this:
```
Creating ''native-messaging-hosts'' folder in ~/.mozilla
Copying native messaging manifest to /home/<username>/.mozilla/native-messaging-hosts/pywalfox.json
Setting path to daemon/pywalfox.py in the manifest
Setting execution permissions on daemon/pywalfox.py
Finished.
```

Restart Firefox and you should be able to fetch the colors using the Settings page. If not, take a look in the Troubleshooting section below.

### Updating the theme using the terminal
If you are using some script for theming your system and do not want to manually refetch your pywal colors using the settings page, you can trigger an update of the browser theme by running `./daemon/pywalfox.py update` in your terminal (the script is not in your `PATH` by default).

### Using the included userChrome.css and userContent.css
Pywalfox comes with custom CSS that you can enable if you want to. It applies your pywal colors to context-menus and other elements of the browser that are not available using the Theme API. If you want to use this feature, you must do the following:
1. Navigate to `about:config` in Firefox
2. Set `toolkit.legacyUserProfileCustomizations.stylesheets` to `true`
3. Enable the `Custom CSS` option in the Settings page of the addon

### Troubleshooting
* If you updated Pywalfox and have issues, try re-running the setup script as described above.
* If you do not have permission to copy files to `.mozilla/native-messaging-hosts`, you can either run \\
`chown <username> ~/.mozilla/native-messaging-host` or (if the folder is empty) simply remove it and the setup script will recreate it with the correct permissions.
* Take a look at the Debugging output in the Settings page of the addon
* Make sure that `path` in `~/.mozilla/native-messaging-hosts/pywalfox.json` points to the location of `daemon/pywalfox.py`
* Make sure that `pywalfox.py` is executable (`chmod +x pywalfox.py`)
* Make sure that the file `~/.cache/wal/colors` exists and contains the colors generated by pywal
* Take a look at the Browser console (`Tools > Web developer > Browser console`) for errors

#### Errors in browser console
- `ExtensionError: No such native application pywalfox`

   The manifest is not installed properly. Follow the instructions here: https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/Native_manifests. The manifest is located at `daemon/assets/pywalfox-manifest.json`.

   If you still can not get it to work, you could try reinstalling Firefox: [#14](https://github.com/Frewacom/Pywalfox/issues/14)

- `Unchecked lastError value: Error: Could not establish connection. Receiving end does not exist.`

   The path to the script in the manifest is invalid or the script crashed on execution (try running it manually).

   The script runs in an infinite loop, so as long as the script does not crash when running it, we know that the issues lies somewhere else.

If you encounter any other errors: https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/Native_messaging#Troubleshooting
', '2020-08-11 15:38:28');
INSERT INTO `rzp-database`.post (post_id, user_fk, category_fk, published, views, title, slug, excerpt, body, date_posted) VALUES (13, 1, 1, 1, 0, 'Gorilla', 'gorilla', 'Package `gorilla/mux` implements a request router and dispatcher for matching incoming requests to
their respective handler.', '# gorilla/mux

[![GoDoc](https://godoc.org/github.com/gorilla/mux?status.svg)](https://godoc.org/github.com/gorilla/mux)
[![CircleCI](https://circleci.com/gh/gorilla/mux.svg?style=svg)](https://circleci.com/gh/gorilla/mux)
[![Sourcegraph](https://sourcegraph.com/github.com/gorilla/mux/-/badge.svg)](https://sourcegraph.com/github.com/gorilla/mux?badge)

![Gorilla Logo](https://cloud-cdn.questionable.services/gorilla-icon-64.png)

https://www.gorillatoolkit.org/pkg/mux

Package `gorilla/mux` implements a request router and dispatcher for matching incoming requests to
their respective handler.

The name mux stands for "HTTP request multiplexer". Like the standard `http.ServeMux`, `mux.Router` matches incoming requests against a list of registered routes and calls a handler for the route that matches the URL or other conditions. The main features are:

* It implements the `http.Handler` interface so it is compatible with the standard `http.ServeMux`.
* Requests can be matched based on URL host, path, path prefix, schemes, header and query values, HTTP methods or using custom matchers.
* URL hosts, paths and query values can have variables with an optional regular expression.
* Registered URLs can be built, or "reversed", which helps maintaining references to resources.
* Routes can be used as subrouters: nested routes are only tested if the parent route matches. This is useful to define groups of routes that share common conditions like a host, a path prefix or other repeated attributes. As a bonus, this optimizes request matching.

---

* [Install](#install)
* [Examples](#examples)
* [Matching Routes](#matching-routes)
* [Static Files](#static-files)
* [Serving Single Page Applications](#serving-single-page-applications) (e.g. React, Vue, Ember.js, etc.)
* [Registered URLs](#registered-urls)
* [Walking Routes](#walking-routes)
* [Graceful Shutdown](#graceful-shutdown)
* [Middleware](#middleware)
* [Handling CORS Requests](#handling-cors-requests)
* [Testing Handlers](#testing-handlers)
* [Full Example](#full-example)

---

## Install

With a [correctly configured](https://golang.org/doc/install#testing) Go toolchain:

```sh
go get -u github.com/gorilla/mux
```

## Examples

Let''s start registering a couple of URL paths and handlers:

```go
func main() {
    r := mux.NewRouter()
    r.HandleFunc("/", HomeHandler)
    r.HandleFunc("/products", ProductsHandler)
    r.HandleFunc("/articles", ArticlesHandler)
    http.Handle("/", r)
}
```

Here we register three routes mapping URL paths to handlers. This is equivalent to how `http.HandleFunc()` works: if an incoming request URL matches one of the paths, the corresponding handler is called passing (`http.ResponseWriter`, `*http.Request`) as parameters.

Paths can have variables. They are defined using the format `{name}` or `{name:pattern}`. If a regular expression pattern is not defined, the matched variable will be anything until the next slash. For example:

```go
r := mux.NewRouter()
r.HandleFunc("/products/{key}", ProductHandler)
r.HandleFunc("/articles/{category}/", ArticlesCategoryHandler)
r.HandleFunc("/articles/{category}/{id:[0-9]+}", ArticleHandler)
```

The names are used to create a map of route variables which can be retrieved calling `mux.Vars()`:

```go
func ArticlesCategoryHandler(w http.ResponseWriter, r *http.Request) {
    vars := mux.Vars(r)
    w.WriteHeader(http.StatusOK)
    fmt.Fprintf(w, "Category: %v\\n", vars["category"])
}
```

And this is all you need to know about the basic usage. More advanced options are explained below.

### Matching Routes

Routes can also be restricted to a domain or subdomain. Just define a host pattern to be matched. They can also have variables:

```go
r := mux.NewRouter()
// Only matches if domain is "www.example.com".
r.Host("www.example.com")
// Matches a dynamic subdomain.
r.Host("{subdomain:[a-z]+}.example.com")
```

There are several other matchers that can be added. To match path prefixes:

```go
r.PathPrefix("/products/")
```

...or HTTP methods:

```go
r.Methods("GET", "POST")
```

...or URL schemes:

```go
r.Schemes("https")
```

...or header values:

```go
r.Headers("X-Requested-With", "XMLHttpRequest")
```

...or query values:

```go
r.Queries("key", "value")
```

...or to use a custom matcher function:

```go
r.MatcherFunc(func(r *http.Request, rm *RouteMatch) bool {
    return r.ProtoMajor == 0
})
```

...and finally, it is possible to combine several matchers in a single route:

```go
r.HandleFunc("/products", ProductsHandler).
  Host("www.example.com").
  Methods("GET").
  Schemes("http")
```

Routes are tested in the order they were added to the router. If two routes match, the first one wins:

```go
r := mux.NewRouter()
r.HandleFunc("/specific", specificHandler)
r.PathPrefix("/").Handler(catchAllHandler)
```

Setting the same matching conditions again and again can be boring, so we have a way to group several routes that share the same requirements. We call it "subrouting".

For example, let''s say we have several URLs that should only match when the host is `www.example.com`. Create a route for that host and get a "subrouter" from it:

```go
r := mux.NewRouter()
s := r.Host("www.example.com").Subrouter()
```

Then register routes in the subrouter:

```go
s.HandleFunc("/products/", ProductsHandler)
s.HandleFunc("/products/{key}", ProductHandler)
s.HandleFunc("/articles/{category}/{id:[0-9]+}", ArticleHandler)
```

The three URL paths we registered above will only be tested if the domain is `www.example.com`, because the subrouter is tested first. This is not only convenient, but also optimizes request matching. You can create subrouters combining any attribute matchers accepted by a route.

Subrouters can be used to create domain or path "namespaces": you define subrouters in a central place and then parts of the app can register its paths relatively to a given subrouter.

There''s one more thing about subroutes. When a subrouter has a path prefix, the inner routes use it as base for their paths:

```go
r := mux.NewRouter()
s := r.PathPrefix("/products").Subrouter()
// "/products/"
s.HandleFunc("/", ProductsHandler)
// "/products/{key}/"
s.HandleFunc("/{key}/", ProductHandler)
// "/products/{key}/details"
s.HandleFunc("/{key}/details", ProductDetailsHandler)
```


### Static Files

Note that the path provided to `PathPrefix()` represents a "wildcard": calling
`PathPrefix("/static/").Handler(...)` means that the handler will be passed any
request that matches "/static/\\*". This makes it easy to serve static files with mux:

```go
func main() {
    var dir string

    flag.StringVar(&dir, "dir", ".", "the directory to serve files from. Defaults to the current dir")
    flag.Parse()
    r := mux.NewRouter()

    // This will serve files under http://localhost:8000/static/<filename>
    r.PathPrefix("/static/").Handler(http.StripPrefix("/static/", http.FileServer(http.Dir(dir))))

    srv := &http.Server{
        Handler:      r,
        Addr:         "127.0.0.1:8000",
        // Good practice: enforce timeouts for servers you create!
        WriteTimeout: 15 * time.Second,
        ReadTimeout:  15 * time.Second,
    }

    log.Fatal(srv.ListenAndServe())
}
```

### Serving Single Page Applications

Most of the time it makes sense to serve your SPA on a separate web server from your API,
but sometimes it''s desirable to serve them both from one place. It''s possible to write a simple
handler for serving your SPA (for use with React Router''s [BrowserRouter](https://reacttraining.com/react-router/web/api/BrowserRouter) for example), and leverage
mux''s powerful routing for your API endpoints.

```go
package main

import (
	"encoding/json"
	"log"
	"net/http"
	"os"
	"path/filepath"
	"time"

	"github.com/gorilla/mux"
)

// spaHandler implements the http.Handler interface, so we can use it
// to respond to HTTP requests. The path to the static directory and
// path to the index file within that static directory are used to
// serve the SPA in the given static directory.
type spaHandler struct {
	staticPath string
	indexPath  string
}

// ServeHTTP inspects the URL path to locate a file within the static dir
// on the SPA handler. If a file is found, it will be served. If not, the
// file located at the index path on the SPA handler will be served. This
// is suitable behavior for serving an SPA (single page application).
func (h spaHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
    // get the absolute path to prevent directory traversal
	path, err := filepath.Abs(r.URL.Path)
	if err != nil {
        // if we failed to get the absolute path respond with a 400 bad request
        // and stop
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

    // prepend the path with the path to the static directory
	path = filepath.Join(h.staticPath, path)

    // check whether a file exists at the given path
	_, err = os.Stat(path)
	if os.IsNotExist(err) {
		// file does not exist, serve index.html
		http.ServeFile(w, r, filepath.Join(h.staticPath, h.indexPath))
		return
	} else if err != nil {
        // if we got an error (that wasn''t that the file doesn''t exist) stating the
        // file, return a 500 internal server error and stop
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

    // otherwise, use http.FileServer to serve the static dir
	http.FileServer(http.Dir(h.staticPath)).ServeHTTP(w, r)
}

func main() {
	router := mux.NewRouter()

	router.HandleFunc("/api/health", func(w http.ResponseWriter, r *http.Request) {
		// an example API handler
		json.NewEncoder(w).Encode(map[string]bool{"ok": true})
	})

	spa := spaHandler{staticPath: "build", indexPath: "index.html"}
	router.PathPrefix("/").Handler(spa)

	srv := &http.Server{
		Handler: router,
		Addr:    "127.0.0.1:8000",
		// Good practice: enforce timeouts for servers you create!
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}

	log.Fatal(srv.ListenAndServe())
}
```

### Registered URLs

Now let''s see how to build registered URLs.

Routes can be named. All routes that define a name can have their URLs built, or "reversed". We define a name calling `Name()` on a route. For example:

```go
r := mux.NewRouter()
r.HandleFunc("/articles/{category}/{id:[0-9]+}", ArticleHandler).
  Name("article")
```

To build a URL, get the route and call the `URL()` method, passing a sequence of key/value pairs for the route variables. For the previous route, we would do:

```go
url, err := r.Get("article").URL("category", "technology", "id", "42")
```

...and the result will be a `url.URL` with the following path:

```
"/articles/technology/42"
```

This also works for host and query value variables:

```go
r := mux.NewRouter()
r.Host("{subdomain}.example.com").
  Path("/articles/{category}/{id:[0-9]+}").
  Queries("filter", "{filter}").
  HandlerFunc(ArticleHandler).
  Name("article")

// url.String() will be "http://news.example.com/articles/technology/42?filter=gorilla"
url, err := r.Get("article").URL("subdomain", "news",
                                 "category", "technology",
                                 "id", "42",
                                 "filter", "gorilla")
```

All variables defined in the route are required, and their values must conform to the corresponding patterns. These requirements guarantee that a generated URL will always match a registered route -- the only exception is for explicitly defined "build-only" routes which never match.

Regex support also exists for matching Headers within a route. For example, we could do:

```go
r.HeadersRegexp("Content-Type", "application/(text|json)")
```

...and the route will match both requests with a Content-Type of `application/json` as well as `application/text`

There''s also a way to build only the URL host or path for a route: use the methods `URLHost()` or `URLPath()` instead. For the previous route, we would do:

```go
// "http://news.example.com/"
host, err := r.Get("article").URLHost("subdomain", "news")

// "/articles/technology/42"
path, err := r.Get("article").URLPath("category", "technology", "id", "42")
```

And if you use subrouters, host and path defined separately can be built as well:

```go
r := mux.NewRouter()
s := r.Host("{subdomain}.example.com").Subrouter()
s.Path("/articles/{category}/{id:[0-9]+}").
  HandlerFunc(ArticleHandler).
  Name("article")

// "http://news.example.com/articles/technology/42"
url, err := r.Get("article").URL("subdomain", "news",
                                 "category", "technology",
                                 "id", "42")
```

### Walking Routes

The `Walk` function on `mux.Router` can be used to visit all of the routes that are registered on a router. For example,
the following prints all of the registered routes:

```go
package main

import (
	"fmt"
	"net/http"
	"strings"

	"github.com/gorilla/mux"
)

func handler(w http.ResponseWriter, r *http.Request) {
	return
}

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/", handler)
	r.HandleFunc("/products", handler).Methods("POST")
	r.HandleFunc("/articles", handler).Methods("GET")
	r.HandleFunc("/articles/{id}", handler).Methods("GET", "PUT")
	r.HandleFunc("/authors", handler).Queries("surname", "{surname}")
	err := r.Walk(func(route *mux.Route, router *mux.Router, ancestors []*mux.Route) error {
		pathTemplate, err := route.GetPathTemplate()
		if err == nil {
			fmt.Println("ROUTE:", pathTemplate)
		}
		pathRegexp, err := route.GetPathRegexp()
		if err == nil {
			fmt.Println("Path regexp:", pathRegexp)
		}
		queriesTemplates, err := route.GetQueriesTemplates()
		if err == nil {
			fmt.Println("Queries templates:", strings.Join(queriesTemplates, ","))
		}
		queriesRegexps, err := route.GetQueriesRegexp()
		if err == nil {
			fmt.Println("Queries regexps:", strings.Join(queriesRegexps, ","))
		}
		methods, err := route.GetMethods()
		if err == nil {
			fmt.Println("Methods:", strings.Join(methods, ","))
		}
		fmt.Println()
		return nil
	})

	if err != nil {
		fmt.Println(err)
	}

	http.Handle("/", r)
}
```

### Graceful Shutdown

Go 1.8 introduced the ability to [gracefully shutdown](https://golang.org/doc/go1.8#http_shutdown) a `*http.Server`. Here''s how to do that alongside `mux`:

```go
package main

import (
    "context"
    "flag"
    "log"
    "net/http"
    "os"
    "os/signal"
    "time"

    "github.com/gorilla/mux"
)

func main() {
    var wait time.Duration
    flag.DurationVar(&wait, "graceful-timeout", time.Second * 15, "the duration for which the server gracefully wait for existing connections to finish - e.g. 15s or 1m")
    flag.Parse()

    r := mux.NewRouter()
    // Add your routes as needed

    srv := &http.Server{
        Addr:         "0.0.0.0:8080",
        // Good practice to set timeouts to avoid Slowloris attacks.
        WriteTimeout: time.Second * 15,
        ReadTimeout:  time.Second * 15,
        IdleTimeout:  time.Second * 60,
        Handler: r, // Pass our instance of gorilla/mux in.
    }

    // Run our server in a goroutine so that it doesn''t block.
    go func() {
        if err := srv.ListenAndServe(); err != nil {
            log.Println(err)
        }
    }()

    c := make(chan os.Signal, 1)
    // We''ll accept graceful shutdowns when quit via SIGINT (Ctrl+C)
    // SIGKILL, SIGQUIT or SIGTERM (Ctrl+/) will not be caught.
    signal.Notify(c, os.Interrupt)

    // Block until we receive our signal.
    <-c

    // Create a deadline to wait for.
    ctx, cancel := context.WithTimeout(context.Background(), wait)
    defer cancel()
    // Doesn''t block if no connections, but will otherwise wait
    // until the timeout deadline.
    srv.Shutdown(ctx)
    // Optionally, you could run srv.Shutdown in a goroutine and block on
    // <-ctx.Done() if your application should wait for other services
    // to finalize based on context cancellation.
    log.Println("shutting down")
    os.Exit(0)
}
```

### Middleware

Mux supports the addition of middlewares to a [Router](https://godoc.org/github.com/gorilla/mux#Router), which are executed in the order they are added if a match is found, including its subrouters.
Middlewares are (typically) small pieces of code which take one request, do something with it, and pass it down to another middleware or the final handler. Some common use cases for middleware are request logging, header manipulation, or `ResponseWriter` hijacking.

Mux middlewares are defined using the de facto standard type:

```go
type MiddlewareFunc func(http.Handler) http.Handler
```

Typically, the returned handler is a closure which does something with the http.ResponseWriter and http.Request passed to it, and then calls the handler passed as parameter to the MiddlewareFunc. This takes advantage of closures being able access variables from the context where they are created, while retaining the signature enforced by the receivers.

A very basic middleware which logs the URI of the request being handled could be written as:

```go
func loggingMiddleware(next http.Handler) http.Handler {
    return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
        // Do stuff here
        log.Println(r.RequestURI)
        // Call the next handler, which can be another middleware in the chain, or the final handler.
        next.ServeHTTP(w, r)
    })
}
```

Middlewares can be added to a router using `Router.Use()`:

```go
r := mux.NewRouter()
r.HandleFunc("/", handler)
r.Use(loggingMiddleware)
```

A more complex authentication middleware, which maps session token to users, could be written as:

```go
// Define our struct
type authenticationMiddleware struct {
	tokenUsers map[string]string
}

// Initialize it somewhere
func (amw *authenticationMiddleware) Populate() {
	amw.tokenUsers["00000000"] = "user0"
	amw.tokenUsers["aaaaaaaa"] = "userA"
	amw.tokenUsers["05f717e5"] = "randomUser"
	amw.tokenUsers["deadbeef"] = "user0"
}

// Middleware function, which will be called for each request
func (amw *authenticationMiddleware) Middleware(next http.Handler) http.Handler {
    return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
        token := r.Header.Get("X-Session-Token")

        if user, found := amw.tokenUsers[token]; found {
        	// We found the token in our map
        	log.Printf("Authenticated user %s\\n", user)
        	// Pass down the request to the next middleware (or final handler)
        	next.ServeHTTP(w, r)
        } else {
        	// Write an error and stop the handler chain
        	http.Error(w, "Forbidden", http.StatusForbidden)
        }
    })
}
```

```go
r := mux.NewRouter()
r.HandleFunc("/", handler)

amw := authenticationMiddleware{}
amw.Populate()

r.Use(amw.Middleware)
```

Note: The handler chain will be stopped if your middleware doesn''t call `next.ServeHTTP()` with the corresponding parameters. This can be used to abort a request if the middleware writer wants to. Middlewares _should_ write to `ResponseWriter` if they _are_ going to terminate the request, and they _should not_ write to `ResponseWriter` if they _are not_ going to terminate it.

### Handling CORS Requests

[CORSMethodMiddleware](https://godoc.org/github.com/gorilla/mux#CORSMethodMiddleware) intends to make it easier to strictly set the `Access-Control-Allow-Methods` response header.

* You will still need to use your own CORS handler to set the other CORS headers such as `Access-Control-Allow-Origin`
* The middleware will set the `Access-Control-Allow-Methods` header to all the method matchers (e.g. `r.Methods(http.MethodGet, http.MethodPut, http.MethodOptions)` -> `Access-Control-Allow-Methods: GET,PUT,OPTIONS`) on a route
* If you do not specify any methods, then:
> _Important_: there must be an `OPTIONS` method matcher for the middleware to set the headers.

Here is an example of using `CORSMethodMiddleware` along with a custom `OPTIONS` handler to set all the required CORS headers:

```go
package main

import (
	"net/http"
	"github.com/gorilla/mux"
)

func main() {
    r := mux.NewRouter()

    // IMPORTANT: you must specify an OPTIONS method matcher for the middleware to set CORS headers
    r.HandleFunc("/foo", fooHandler).Methods(http.MethodGet, http.MethodPut, http.MethodPatch, http.MethodOptions)
    r.Use(mux.CORSMethodMiddleware(r))
    
    http.ListenAndServe(":8080", r)
}

func fooHandler(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Access-Control-Allow-Origin", "*")
    if r.Method == http.MethodOptions {
        return
    }

    w.Write([]byte("foo"))
}
```

And an request to `/foo` using something like:

```bash
curl localhost:8080/foo -v
```

Would look like:

```bash
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /foo HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.59.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Access-Control-Allow-Methods: GET,PUT,PATCH,OPTIONS
< Access-Control-Allow-Origin: *
< Date: Fri, 28 Jun 2019 20:13:30 GMT
< Content-Length: 3
< Content-Type: text/plain; charset=utf-8
< 
* Connection #0 to host localhost left intact
foo
```

### Testing Handlers

Testing handlers in a Go web application is straightforward, and _mux_ doesn''t complicate this any further. Given two files: `endpoints.go` and `endpoints_test.go`, here''s how we''d test an application using _mux_.

First, our simple HTTP handler:

```go
// endpoints.go
package main

func HealthCheckHandler(w http.ResponseWriter, r *http.Request) {
    // A very simple health check.
    w.Header().Set("Content-Type", "application/json")
    w.WriteHeader(http.StatusOK)

    // In the future we could report back on the status of our DB, or our cache
    // (e.g. Redis) by performing a simple PING, and include them in the response.
    io.WriteString(w, `{"alive": true}`)
}

func main() {
    r := mux.NewRouter()
    r.HandleFunc("/health", HealthCheckHandler)

    log.Fatal(http.ListenAndServe("localhost:8080", r))
}
```

Our test code:

```go
// endpoints_test.go
package main

import (
    "net/http"
    "net/http/httptest"
    "testing"
)

func TestHealthCheckHandler(t *testing.T) {
    // Create a request to pass to our handler. We don''t have any query parameters for now, so we''ll
    // pass ''nil'' as the third parameter.
    req, err := http.NewRequest("GET", "/health", nil)
    if err != nil {
        t.Fatal(err)
    }

    // We create a ResponseRecorder (which satisfies http.ResponseWriter) to record the response.
    rr := httptest.NewRecorder()
    handler := http.HandlerFunc(HealthCheckHandler)

    // Our handlers satisfy http.Handler, so we can call their ServeHTTP method
    // directly and pass in our Request and ResponseRecorder.
    handler.ServeHTTP(rr, req)

    // Check the status code is what we expect.
    if status := rr.Code; status != http.StatusOK {
        t.Errorf("handler returned wrong status code: got %v want %v",
            status, http.StatusOK)
    }

    // Check the response body is what we expect.
    expected := `{"alive": true}`
    if rr.Body.String() != expected {
        t.Errorf("handler returned unexpected body: got %v want %v",
            rr.Body.String(), expected)
    }
}
```

In the case that our routes have [variables](#examples), we can pass those in the request. We could write
[table-driven tests](https://dave.cheney.net/2013/06/09/writing-table-driven-tests-in-go) to test multiple
possible route variables as needed.

```go
// endpoints.go
func main() {
    r := mux.NewRouter()
    // A route with a route variable:
    r.HandleFunc("/metrics/{type}", MetricsHandler)

    log.Fatal(http.ListenAndServe("localhost:8080", r))
}
```

Our test file, with a table-driven test of `routeVariables`:

```go
// endpoints_test.go
func TestMetricsHandler(t *testing.T) {
    tt := []struct{
        routeVariable string
        shouldPass bool
    }{
        {"goroutines", true},
        {"heap", true},
        {"counters", true},
        {"queries", true},
        {"adhadaeqm3k", false},
    }

    for _, tc := range tt {
        path := fmt.Sprintf("/metrics/%s", tc.routeVariable)
        req, err := http.NewRequest("GET", path, nil)
        if err != nil {
            t.Fatal(err)
        }

        rr := httptest.NewRecorder()
	
	// Need to create a router that we can pass the request through so that the vars will be added to the context
	router := mux.NewRouter()
        router.HandleFunc("/metrics/{type}", MetricsHandler)
        router.ServeHTTP(rr, req)

        // In this case, our MetricsHandler returns a non-200 response
        // for a route variable it doesn''t know about.
        if rr.Code == http.StatusOK && !tc.shouldPass {
            t.Errorf("handler should have failed on routeVariable %s: got %v want %v",
                tc.routeVariable, rr.Code, http.StatusOK)
        }
    }
}
```

## Full Example

Here''s a complete, runnable example of a small `mux` based server:

```go
package main

import (
    "net/http"
    "log"
    "github.com/gorilla/mux"
)

func YourHandler(w http.ResponseWriter, r *http.Request) {
    w.Write([]byte("Gorilla!\\n"))
}

func main() {
    r := mux.NewRouter()
    // Routes consist of a path and a handler function.
    r.HandleFunc("/", YourHandler)

    // Bind to a port and pass our router in
    log.Fatal(http.ListenAndServe(":8000", r))
}
```

## License

BSD licensed. See the LICENSE file for details.', '2020-08-12 17:03:08');
