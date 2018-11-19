# Catapult - File parser for CAT Tools with CAT Analysis

# Table of contents

- Reasoning behind Catapult
- What is XTRF?
- How to use Catapult?
- Contributing
- Contribution-to-XTRF merge policy

## Reasoning

- [x] Solution used by XTRF as Cat Tool Analysis Mechanism
- [x] Multiple CAT Tools Supported (Across, Dejavu, Fortis, Idiom, Logoport, Memoq, Memoq_Trados, Memsource, Multitrans, Passolo, Passolo2016, SDL, SDL_WorldServer, SDLX, Trados, Transit, WordFast, XTM)
- [x] Over time, there are new CAT Tools or upgrades to existing ones, which is what XTRF is trying best to keep up with. It takes time and resources to do so and although XTRF's Team tries their best to implement and adapt the system to newest standards, it takes some time and might not be instantly available, whenever such improvement rolls out. By open sourcing such project, any contributor can add/modify a parser to new/updated system that can result in both spreading the great idea of Open Source software and quicker implementation within XTRF.
- [x] Tools for CAT Analysis, available within this project, can be used freely in any solutions you're working on.

## XTRF

XTRF Language Business Platform is a translation management system highly customizable to meet your needs in successfully completing a translation project. Without a doubt, XTRF is a powerful tool that offers practical business solutions by minimizing the delivery time of your project, efficiently assigning different vendors to each job, and organizing the entire process in a fast and task-oriented manner.

## How to use Catapult?

It's as simple as importing a file, converting it to byte array than parsing with automated CATAnalysisCompositeParser.

```
InputStream inputStream = new FileInputStream(fileName);
ByteArrayInputStream bais = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
CATAnalysisCompositeParser parser = CATAnalysisCompositeParser.newInstance();
CATAnalysis parsedAnalysis = parser.parse(fileName, bais);
```

## Contributing

Check CONTRIBUTING for further details.

## Contribution-to-XTRF merge policy

After adding changes and creating pull request, the code will be reviewed by XTRF team, tested and accepted, if the changes are correct. After these steps newest Catapult library will be released with latest XTRF patch release.
